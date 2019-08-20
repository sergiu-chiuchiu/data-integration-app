package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.entities.*;
import org.devon.app.entities.enums.CurrencyType;
import org.devon.app.entities.enums.PageSource;
import org.devon.app.entities.enums.Partitioning;
import org.devon.app.entities.transformers.AdvertisementPageTTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.exceptions.EmptyFieldException;
import org.devon.app.repositories.AdvertisementPageRepository;
import org.devon.app.services.IintegrationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class IntegrationServiceT  implements IintegrationService {
    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    @Autowired
    AdvertisementPageRepository advertisementPageRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Class<? extends AdvertisementPageTransformer>> mapStreamToTransformer(BufferedReader br) {
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(br).withCSVParser(parser).build();

            List<String> header = Arrays.asList(csvReader.readNext());
            header.set(0, header.get(0).substring(1));

            String[] nextRecord;
            int cnt = 0;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("<-- " + String.valueOf(cnt++) + " -->");

                AdvertisementPageTTransformer tTransformer = new AdvertisementPageTTransformer();
                tTransformer.setPageSource("T");
                int c = 0;
                for (String cell : nextRecord) {
                    mapItemToTransformer(header.get(c), nextRecord[c], tTransformer);
                    c++;
//                    System.out.print(cell + " == " + String.valueOf(c) + " == \t");
                }

                Boolean isDuplicateWithin = checkForDuplicatesWithin(tTransformer);
                if (isDuplicateWithin) {
                    Boolean isModifiedRecord = checkForRecordUpdate(tTransformer);
                    if (isModifiedRecord) {
                        AdvertisementPage updatedAp = mapTransformerToEntities(tTransformer);
                        AdvertisementPage ap = advertisementPageRepository.findByPageId(updatedAp.getPageId());
                        modelMapper.map(updatedAp, ap);
                        advertisementPageRepository.save(ap);
                    }
                } else {
                    AdvertisementPageTTransformer duplicateAP = checkForDuplicatesBetween(tTransformer);
                    if (duplicateAP == null) {
                        AdvertisementPage ap = mapTransformerToEntities(tTransformer);
                        advertisementPageRepository.save(ap);
                    } else {
//                        Boolean isModifiedRecord = checkForRecordBetweenUpdate(tTransformer, duplicateAP);
//                        if (isModifiedRecord) {
//                            AdvertisementPage ap = mapTransformerToEntities(tTransformer);
//                            advertisementPageRepository.save(ap);
//                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void mapItemToTransformer(String header, String item, AdvertisementPageTTransformer tTransformer) {
        try {
            if (item.equals("")) {
                throw new EmptyFieldException(header + " field is empty");
            }

            switch (header) {
                case "PageTitle":
//                    System.out.println("good " + item);
                    tTransformer.setPageTitle(item);
                    break;
                case "Zone":
//                    System.out.println("good" + item);
                    tTransformer.convertZone(item);
                    break;
                case "Price":
//                    System.out.println("good " + item);
                    tTransformer.convertToPriceList(item);
                    break;
                case "NoOfRooms":
//                    System.out.println("good " + item);
                    tTransformer.convertToNoOfRooms(item);
                    break;
                case "UsefulArea":
//                    System.out.println("good " + item);
                    tTransformer.convertToUsefulArea(item);
                    break;
                case "BuiltSurface":
//                    System.out.println("good " + item);
                    tTransformer.convertToBuiltSurface(item);
                    break;
                case "Partitioning":
//                    System.out.println("good " + item);
                    tTransformer.setPartitioning(item);
                    break;
                case "Floor":
//                    System.out.println("good " + item);
                    tTransformer.convertFloorItem(item);
                    break;
                case "NoOfBathrooms":
//                    System.out.println("good " + item);
                    tTransformer.convertToNoOfBathrooms(item);
                    break;
                case "ConstructionYear":
//                    System.out.println("good " + item);
                    tTransformer.convertToConstructionYear(item);
                    break;
                case "PropertyType":
//                    System.out.println("good " + item);
                    tTransformer.convertToBuildingType(item);
                    break;
                case "TotalFloors":
//                    System.out.println("good " + item);
                    tTransformer.convertToTotalFloors(item);
                    break;
                case "PageId":
//                    System.out.println("good " + item);
                    tTransformer.setPageId(item);
                    break;
                case "LastUpdate":
//                    System.out.println("good " + item);
                    tTransformer.convertToLastUpdated(item);
                    break;
                case "State":
//                    System.out.println("good " + item);
                    tTransformer.setEstateState(item);
                    break;
                case "Image1":
//                    System.out.println("good " + item);
                    tTransformer.setImage1(item);
                    break;
                case "Image2":
//                    System.out.println("good " + item);
                    tTransformer.setImage2(item);
                    break;
                default:
                    LOG.error("The header " + header + " could not be matched with a case");
                    break;
            }
        } catch (EmptyFieldException e) {
            LOG.warn(header + " field is empty");
        }
    }

    @Override
    public  checkForDuplicatesBetween(AdvertisementPage apToCheck) {
        //TODO
        return null;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin(E tTransformer) {
        List<AdvertisementPage> advertisementPageList = advertisementPageRepository.findByPageSource(PageSource.fromString(tTransformer.getPageSource()));

        for (AdvertisementPage ap : advertisementPageList) {
            if (tTransformer.getPageId().equals(ap.getPageId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E tTransformer) {
        AdvertisementPage ap = advertisementPageRepository.findByPageId(tTransformer.getPageId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Boolean isDateEqual = sdf.format(ap.getEditDate().getTime()).equals(sdf.format(tTransformer.getLastUpdated().getTime()));
        if(isDateEqual) {
            return false;
        }
        return true;
    }

    @Override
    public <E extends AdvertisementPageTransformer> AdvertisementPage mapTransformerToEntities(E advertisementPageTransformer) {
        AdvertisementPageTTransformer apTr = (AdvertisementPageTTransformer) advertisementPageTransformer;

        Area area = Area.builder()
                .usefulArea(apTr.getUsefulArea())
                .builtSurface(apTr.getBuiltSurface())
                .build();

        Construction construction = Construction.builder()
                .constructionYear(apTr.getConstructionYear())
                .buildingType(apTr.getBuildingType())
                .floor(apTr.getFloorNo())
                .totalNoOfFloors(apTr.getTotalFloors())
                .build();

        Rooms rooms = Rooms.builder()
                .noOfRooms(apTr.getNoOfRooms())
                .noOfBathrooms(apTr.getNoOfBathrooms())
                .build();

        Estate estate = Estate.builder()
                .partitioning(Partitioning.fromString(apTr.getPartitioning()))
                .region(apTr.getRegion())
                .neighbourhood(apTr.getNeighbourhood())
                .estateState(apTr.getEstateState())
                .area(area)
                .construction(construction)
                .rooms(rooms)
                .build();

        AdvertisementPage ap = AdvertisementPage.builder()
                .pageTitle(apTr.getPageTitle())
                .estate(estate)
                .pageId(apTr.getPageId())
                .editDate(apTr.getLastUpdated())
                .pageSource(PageSource.fromString(apTr.getPageSource()))
                .build();

        List<Price> priceList = new ArrayList<>();
        for (Map.Entry<String, Double> priceEntry : apTr.getPriceList().entrySet()) {
            Price p = Price.builder()
                    .currencyType(CurrencyType.fromString(priceEntry.getKey()))
                    .price(priceEntry.getValue())
                    .advertisementPage(ap)
                    .build();
            priceList.add(p);
        }

        List<Image> imageList = new ArrayList<>();
        Image image = Image.builder()
                .imageName(apTr.getImage1())
                .advertisementPage(ap)
                .build();
        imageList.add(image);
        image = Image.builder()
                .imageName(apTr.getImage2())
                .advertisementPage(ap)
                .build();
        imageList.add(image);

        ap.setImageList(imageList);
        ap.setPriceList(priceList);

        return ap;
    }
}

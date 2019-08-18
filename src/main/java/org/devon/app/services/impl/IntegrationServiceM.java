package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.entities.*;
import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.CurrencyType;
import org.devon.app.entities.enums.PageSource;
import org.devon.app.entities.enums.Partitioning;
import org.devon.app.entities.transformers.AdvertisementPageMTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.exceptions.EmptyFieldException;
import org.devon.app.repositories.AdvertisementPageRepository;
import org.devon.app.services.IintegrationService;
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
public class IntegrationServiceM implements IintegrationService {
    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    @Autowired
    AdvertisementPageRepository advertisementPageRepository;

    @Override
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

                AdvertisementPageMTransformer mTransformer = new AdvertisementPageMTransformer();
                mTransformer.setPageSource("M");
                int c = 0;
                for (String cell : nextRecord) {
                    mapItemToTransformer(header.get(c), nextRecord[c], mTransformer);
                    c++;
//                    System.out.print(cell + " == " + String.valueOf(c) + " == \t");
                }

                Boolean isDuplicateWithin = checkForDuplicatesWithin(mTransformer);
                if (isDuplicateWithin) {
                    Boolean isModifiedRecord = checkForRecordUpdate(mTransformer);
                    if (isModifiedRecord) {
                        AdvertisementPage ap = mapTransformerToEntities(mTransformer);
                        advertisementPageRepository.save(ap);
                    }
                } else {
                    AdvertisementPageTTransformer duplicateAP = checkForDuplicatesBetween(mTransformer);
                    if (duplicateAP == null) {
                        AdvertisementPage ap = mapTransformerToEntities(mTransformer);
                        advertisementPageRepository.save(ap);
                    } else {
//                        Boolean isModifiedRecord = checkForRecordBetweenUpdate(mTransformer, duplicateAP);
//                        if (isModifiedRecord) {
//                            AdvertisementPage ap = mapTransformerToEntities(mTransformer);
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

    @Override
    public <E, F extends AdvertisementPageTransformer> E checkForDuplicatesBetween(F mTransformer) {


        //TOOD
        return null;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin(E mTransformer) {
        List<AdvertisementPage> advertisementPageList = advertisementPageRepository.findByPageSource(PageSource.fromString(mTransformer.getPageSource()));

        for (AdvertisementPage ap : advertisementPageList) {
            if (mTransformer.getPageId().equals(ap.getPageId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E mTransfomer) {
        AdvertisementPage ap = advertisementPageRepository.findByPageId(mTransfomer.getPageId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Boolean isDateEqual = sdf.format(ap.getEditDate().getTime()).equals(sdf.format(mTransfomer.getLastUpdated().getTime()));
        if(isDateEqual) {
            return false;
        }
        return true;
    }

    @Override
    public <E extends AdvertisementPageTransformer> AdvertisementPage mapTransformerToEntities(E advertisementPageTransformer) {
        AdvertisementPageMTransformer apTr = (AdvertisementPageMTransformer) advertisementPageTransformer;

        Area area = Area.builder()
                .usefulArea(apTr.getUsefulArea())
                .builtSurface(apTr.getBuiltSurface())
                .totalUsefulArea(apTr.getTotalUsefulArea())
                .build();

        Construction construction = Construction.builder()
                .constructionYear(apTr.getConstructionYear())
                .resistanceStructure(apTr.getResistanceStructure())
                .buildingType(apTr.getBuildingType())
                .floor(apTr.getFloorNo())
                .totalNoOfFloors(apTr.getTotalFloors())
                .build();

        Rooms rooms = Rooms.builder()
                .noOfRooms(apTr.getNoOfRooms())
                .noOfKitchens(apTr.getNoOfKitchens())
                .noOfBalconies(apTr.getNoOfBalconies())
                .noOfBathrooms(apTr.getNoOfBathrooms())
                .build();

        Estate estate = Estate.builder()
                .partitioning(Partitioning.fromString(apTr.getPartitioning()))
                .comfortType(ComfortType.fromString(apTr.getComfort()))
                .region(apTr.getRegion())
                .neighbourhood(apTr.getNeighbourhood())
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

    private void mapItemToTransformer(String header, String item, AdvertisementPageMTransformer mTransformer) {
        try {
            if (item.equals("")) {
                throw new EmptyFieldException(header + " field is empty");
            }

            switch (header) {
                case "PageTitle":
//                    System.out.println("good " + item);
                    mTransformer.setPageTitle(item);
                    break;
                case "Zone":
//                    System.out.println("good" + item);
                    mTransformer.convertZone(item);
                    break;
                case "PriceEuro":
//                    System.out.println("good " + item);
                    mTransformer.convertToPriceList(item);
                    break;
                case "NoOfRooms":
//                    System.out.println("good " + item);
                    mTransformer.setNoOfRooms(Integer.valueOf(item));
                    break;
                case "UsefulArea":
//                    System.out.println("good " + item);
                    mTransformer.convertToUsefulArea(item);
                    break;
                case "TotalUsefulArea":
//                    System.out.println("good " + item);
                    mTransformer.convertToTotalUsefulArea(item);
                    break;
                case "BuiltSurface":
//                    System.out.println("good " + item);
                    mTransformer.convertToBuiltSurface(item);
                    break;
                case "Partitioning":
//                    System.out.println("good " + item);
                    mTransformer.setPartitioning(item.trim());
                    break;
                case "Comfort":
//                    System.out.println("good " + item);
                    mTransformer.setComfort(item.trim());
                    break;
                case "Floor":
//                    System.out.println("good " + item);
                    mTransformer.convertFloorItem(item);
                    break;
                case "NoOfKitchens":
//                    System.out.println("good " + item);
                    mTransformer.setNoOfKitchens(Integer.valueOf(item));
                    break;
                case "NoOfBathrooms":
//                    System.out.println("good " + item);
                    mTransformer.setNoOfBathrooms(Integer.valueOf(item));
                    break;
                case "ConstructionYear":
//                    System.out.println("good " + item);
                    mTransformer.convertToConstructionYear(item);
                    break;
                case "ResistanceStructure":
//                    System.out.println("good " + item);
                    mTransformer.setResistanceStructure(item.trim());
                    break;
                case "BuildingType":
//                    System.out.println("good " + item);
                    mTransformer.setBuildingType(item.trim());
                    break;
                case "TotalFloors":
//                    System.out.println("good " + item);
//                    mTransformer.setTotalFloors();
                    break;
                case "NoOfBalconies":
//                    System.out.println("good " + item);
                    mTransformer.convertToNoOfBalconies(item);
                    break;
                case "PageId":
//                    System.out.println("good " + item);
                    mTransformer.setPageId(item.trim());
                    break;
                case "LastUpdate":
//                    System.out.println("good " + item);
                    mTransformer.convertToLastUpdated(item);
                    break;
                case "Image1":
//                    System.out.println("good " + item);
                    mTransformer.setImage1(item.trim());
                    break;
                case "Image2":
//                    System.out.println("good " + item);
                    mTransformer.setImage2(item);
                    break;
                default:
                    LOG.error("The header " + header + " could not be matched with a case");
                    break;
            }
        } catch (EmptyFieldException e) {
            LOG.warn(header + " field is empty");
        }
    }

}
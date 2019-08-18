package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.entities.AdvertisementPage;
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
import java.util.Arrays;
import java.util.List;

@Service
public class IntegrationServiceT  implements IintegrationService {
    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    @Autowired
    AdvertisementPageRepository advertisementPageRepository;

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

//                Boolean isDuplicateWithin = checkForDuplicatesWithin(tTransformer);
//                if (isDuplicateWithin) {
//                    Boolean isModifiedRecord = checkForRecordUpdate(tTransformer);
//                    if (isModifiedRecord) {
//                        AdvertisementPage ap = mapTransformerToEntities(tTransformer);
//                        advertisementPageRepository.save(ap);
//                    }
//                } else {
//                    AdvertisementPageTTransformer duplicateAP = checkForDuplicatesBetween(tTransformer);
//                    if (duplicateAP == null) {
//                        AdvertisementPage ap = mapTransformerToEntities(tTransformer);
//                        advertisementPageRepository.save(ap);
//                    } else {
//                        Boolean isModifiedRecord = checkForRecordBetweenUpdate(tTransformer, duplicateAP);
//                        if (isModifiedRecord) {
//                            AdvertisementPage ap = mapTransformerToEntities(tTransformer);
//                            advertisementPageRepository.save(ap);
//                        }
//                    }
//                }
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
                    System.out.println("good " + item);
                    tTransformer.convertToBuiltSurface(item);
                    break;
//                case "Partitioning":
////                    System.out.println("good " + item);
//                    tTransformer.setPartitioning(item.trim());
//                    break;
//                case "Comfort":
////                    System.out.println("good " + item);
//                    tTransformer.setComfort(item.trim());
//                    break;
//                case "Floor":
////                    System.out.println("good " + item);
//                    tTransformer.convertFloorItem(item);
//                    break;
//                case "NoOfKitchens":
////                    System.out.println("good " + item);
//                    tTransformer.setNoOfKitchens(Integer.valueOf(item));
//                    break;
//                case "NoOfBathrooms":
////                    System.out.println("good " + item);
//                    tTransformer.setNoOfBathrooms(Integer.valueOf(item));
//                    break;
//                case "ConstructionYear":
////                    System.out.println("good " + item);
//                    tTransformer.convertToConstructionYear(item);
//                    break;
//                case "ResistanceStructure":
////                    System.out.println("good " + item);
//                    tTransformer.setResistanceStructure(item.trim());
//                    break;
//                case "BuildingType":
////                    System.out.println("good " + item);
//                    tTransformer.setBuildingType(item.trim());
//                    break;
//                case "TotalFloors":
////                    System.out.println("good " + item);
////                    tTransformer.setTotalFloors();
//                    break;
//                case "NoOfBalconies":
////                    System.out.println("good " + item);
//                    tTransformer.convertToNoOfBalconies(item);
//                    break;
//                case "PageId":
////                    System.out.println("good " + item);
//                    tTransformer.setPageId(item.trim());
//                    break;
//                case "LastUpdate":
////                    System.out.println("good " + item);
//                    tTransformer.convertToLastUpdated(item);
//                    break;
//                case "Image1":
////                    System.out.println("good " + item);
//                    tTransformer.setImage1(item.trim());
//                    break;
//                case "Image2":
////                    System.out.println("good " + item);
//                    tTransformer.setImage2(item);
//                    break;
//                default:
//                    LOG.error("The header " + header + " could not be matched with a case");
//                    break;
            }
        } catch (EmptyFieldException e) {
            LOG.warn(header + " field is empty");
        }
    }

    @Override
    public <E, F extends AdvertisementPageTransformer> E checkForDuplicatesBetween(F tTransformer) {
        return null;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin(E tTransformer) {
        return null;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E tTransformer) {
        return null;
    }

    @Override
    public <E extends AdvertisementPageTransformer> AdvertisementPage mapTransformerToEntities(E tTransformer) {
        return null;
    }
}

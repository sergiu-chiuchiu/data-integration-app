package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.entities.decorators.AdvertisementPageDecorator;
import org.devon.app.entities.transformers.AdvertisementPageMTransformer;
import org.devon.app.exceptions.EmptyFieldException;
import org.devon.app.services.IintegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class IntegrationServiceM implements IintegrationService {
    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    @Override
    public List<Class<? extends AdvertisementPageDecorator>> mapStreamToTransformer(BufferedReader br) {

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

                int c = 0;
                for (String cell : nextRecord) {
                    mapItemToTransformer(header.get(c), nextRecord[c]);
                    c++;
//                    System.out.print(cell + " == " + String.valueOf(c) + " == \t");
                }
            }


//            String rawHeader = br.readLine();
//            List<String> header = Arrays.asList(rawHeader.split("\\^"));
//            header.set(0, header.get(0).substring(1));
//
//            System.out.println(rawHeader);
//
//            List<String> csvLineItems;
//            while (br.ready()) {
//                csvLineItems = Arrays.asList(br.readLine().split("\\^"));
//
//                mapItemToTransformer(header, csvLineItems);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void mapItemToTransformer(String header, String item) {
        AdvertisementPageMTransformer mTransformer = new AdvertisementPageMTransformer();
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
                    mTransformer.setPartitioning(item);
                    break;
                case "Comfort":
//                    System.out.println("good " + item);
                    mTransformer.setPartitioning(item);
                    break;
                case "Floor":
//                    System.out.println("good " + item);
                    mTransformer.convertFloorItem(item);
                    break;
//                case "NoOfKitchens":
//                    System.out.println("good " + item);
//                    break;
//                case "NoOfBathrooms":
//                    System.out.println("good " + item);
//                    break;
//                case "ConstructionYear":
//                    System.out.println("good " + item);
//                    break;
//                case "ResistanceStructure":
//                    System.out.println("good " + item);
//                    break;
//                case "BuildingType":
//                    System.out.println("good " + item);
//                    break;
//                case "TotalFloors":
//                    System.out.println("good " + item);
//                    break;
//                case "NoOfBalconies":
//                    System.out.println("good " + item);
//                    break;
//                case "PageId":
//                    System.out.println("good " + item);
//                    break;
//                case "LastUpdate":
//                    System.out.println("good " + item);
//                    break;
//                case "Image1":
//                    System.out.println("good " + item);
//                    break;
//                case "Image2":
//                    System.out.println("good " + item);
//                    break;
//                default:
//                    System.out.println("Error"); break;

            }
        } catch (EmptyFieldException e) {
            LOG.warn(header + " field is empty");
        }


    }

}

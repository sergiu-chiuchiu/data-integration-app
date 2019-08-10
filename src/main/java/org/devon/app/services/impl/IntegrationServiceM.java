package org.devon.app.services.impl;

import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.decorators.AdvertisementPageDecorator;
import org.devon.app.entities.transformers.AdvertisementPageMTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.services.IintegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class IntegrationServiceM implements IintegrationService {

    @Override
    public List<Class<? extends AdvertisementPageDecorator>> mapStreamToTransformer(BufferedReader br) {
        try {
            String rawHeader = br.readLine();
            List<String> header = Arrays.asList(rawHeader.split("\\^"));
            header.set(0, header.get(0).substring(1));

            System.out.println(rawHeader);

            List<String> csvLineItems;
            while (br.ready()) {
                csvLineItems = Arrays.asList(br.readLine().split("\\^"));

                mapItemToTransformer(header, csvLineItems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void mapItemToTransformer(List<String> header, List<String> csvLineItems) {
        AdvertisementPageTransformer mTransformer = new AdvertisementPageMTransformer();

        System.out.println("=================");
        //cam be done with a  hashmap as well
        for (int i = 0; i < header.size(); i++) {
            String item = csvLineItems.get(i);
            switch (header.get(i))  {
                case "PageTitle":
                    System.out.println("good " + csvLineItems.get(i));
                    mTransformer.setPageTitle(csvLineItems.get(i));
                    break;
//                case "Zone":
//                    System.out.println("good");
//                    mTransformer
//                    break;
//                case "PriceEuro":
//                    System.out.println("good");
//                    break;
//                case "NoOfRooms":
//                    System.out.println("good");
//                    break;
//                case "UsefulArea":
//                    System.out.println("good");
//                    break;
//                case "TotalUsefulArea":
//                    System.out.println("good");
//                    break;
//                case "BuiltSurface":
//                    System.out.println("good");
//                    break;
//                case "Partitioning":
//                    System.out.println("good");
//                    break;
//                case "Comfort":
//                    System.out.println("good");
//                    break;
//                case "Floor":
//                    System.out.println("good");
//                    break;
//                case "NoOfKitchens":
//                    System.out.println("good");
//                    break;
//                case "NoOfBathrooms":
//                    System.out.println("good");
//                    break;
//                case "ConstructionYear":
//                    System.out.println("good");
//                    break;
//                case "ResistanceStructure":
//                    System.out.println("good");
//                    break;
//                case "BuildingType":
//                    System.out.println("good");
//                    break;
//                case "TotalFloors":
//                    System.out.println("good");
//                    break;
//                case "NoOfBalconies":
//                    System.out.println("good");
//                    break;
//                case "PageId":
//                    System.out.println("good");
//                    break;
//                case "LastUpdate":
//                    System.out.println("good");
//                    break;
//                case "Image1":
//                    System.out.println("good");
//                    break;
//                case "Image2":
//                    System.out.println("good");
//                    break;
//                default:
//                    System.out.println("Error"); break;
            }

        }


    }

}

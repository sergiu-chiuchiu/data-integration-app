package org.devon.app.services.impl;

import com.opencsv.CSVReader;
import org.devon.app.ConsoleInteractions;
import org.devon.app.comparator.AdvertisementPageComparator;
import org.devon.app.entities.transformers.AdvertisementPageMTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.exceptions.EmptyFieldException;
import org.devon.app.repositories.AdvertisementPageRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Service
public class IntegrationServiceM extends AIntegrationService {
    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    @Autowired
    public IntegrationServiceM(AdvertisementPageRepository advertisementPageRepository, ModelMapper modelMapper, AdvertisementPageComparator advertisementPageComparator, ConsoleInteractions consoleInteractions) {
        this.advertisementPageRepository = advertisementPageRepository;
        this.modelMapper = modelMapper;
        this.advertisementPageComparator = advertisementPageComparator;
        this.consoleInteractions = consoleInteractions;
    }

    @Override
    public List<Class<? extends AdvertisementPageTransformer>> mapStreamToEntities(BufferedReader br) {
        try {
            CSVReader csvReader = csvReaderInit(br);
            List<String> header = getDataHeader(csvReader);

            String[] nextRecord;
            int cnt = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("<-- " + cnt++ + " -->");
                AdvertisementPageMTransformer mTransformer = new AdvertisementPageMTransformer();
                for (int c = 0; c < nextRecord.length; c++) {
                    mapItemToTransformer(header.get(c), nextRecord[c], mTransformer);
                }

                validateTransformerAndPersist(mTransformer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void mapItemToTransformer(String header, String item, AdvertisementPageMTransformer mTransformer) {
        try {
            if (item.equals("")) {
                throw new EmptyFieldException(header + " field is empty");
            }

            switch (header) {
                case "PageTitle":
                    mTransformer.setPageTitle(item);
                    break;
                case "Zone":
                    mTransformer.convertZone(item);
                    break;
                case "PriceEuro":
                    mTransformer.convertToPriceList(item);
                    break;
                case "NoOfRooms":
                    mTransformer.setNoOfRooms(Integer.valueOf(item));
                    break;
                case "UsefulArea":
                    mTransformer.convertToUsefulArea(item);
                    break;
                case "TotalUsefulArea":
                    mTransformer.convertToTotalUsefulArea(item);
                    break;
                case "BuiltSurface":
                    mTransformer.convertToBuiltSurface(item);
                    break;
                case "Partitioning":
                    mTransformer.setPartitioning(item.trim());
                    break;
                case "Comfort":
                    mTransformer.setComfort(item.trim());
                    break;
                case "Floor":
                    mTransformer.convertFloorItem(item);
                    break;
                case "NoOfKitchens":
                    mTransformer.setNoOfKitchens(Integer.valueOf(item));
                    break;
                case "NoOfBathrooms":
                    mTransformer.setNoOfBathrooms(Integer.valueOf(item));
                    break;
                case "ConstructionYear":
                    mTransformer.convertToConstructionYear(item);
                    break;
                case "ResistanceStructure":
                    mTransformer.setResistanceStructure(item.trim());
                    break;
                case "BuildingType":
                    mTransformer.setBuildingType(item.trim());
                    break;
                case "TotalFloors":
//                    mTransformer.setTotalFloors();
                    break;
                case "NoOfBalconies":
                    mTransformer.convertToNoOfBalconies(item);
                    break;
                case "PageId":
                    mTransformer.setPageId(item.trim());
                    break;
                case "LastUpdate":
                    mTransformer.convertToLastUpdated(item);
                    break;
                case "Image1":
                    mTransformer.setImage1(item.trim());
                    break;
                case "Image2":
                    mTransformer.setImage2(item.trim());
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
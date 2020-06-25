package org.devon.app.services.impl;

import com.opencsv.CSVReader;
import org.devon.app.ConsoleInteractions;
import org.devon.app.comparator.AdvertisementPageComparator;
import org.devon.app.dto.RawDataDto;
import org.devon.app.dto.RawDataTDto;
import org.devon.app.entities.transformers.AdvertisementPageTTransformer;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.exceptions.EmptyFieldException;
import org.devon.app.mapper.TransformerMapperT;
import org.devon.app.repositories.AdvertisementPageRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class IntegrationServiceT extends AIntegrationService {

    private static Logger LOG = LoggerFactory
            .getLogger(IntegrationServiceM.class);

    TransformerMapperT transformerMapperT;

    @Autowired
    public IntegrationServiceT(AdvertisementPageRepository advertisementPageRepository,
                               ModelMapper modelMapper,
                               AdvertisementPageComparator advertisementPageComparator,
                               ConsoleInteractions consoleInteractions,
                               TransformerMapperT transformerMapperT) {
        this.advertisementPageRepository = advertisementPageRepository;
        this.modelMapper = modelMapper;
        this.advertisementPageComparator = advertisementPageComparator;
        this.consoleInteractions = consoleInteractions;
        this.transformerMapperT = transformerMapperT;
    }

    @Override
    public <E extends RawDataDto> void mapDtoToTransformer(E rawDataTDto) {
        AdvertisementPageTTransformer advertisementPageTTransformer = transformerMapperT.rawDataTDtoToAdvertisementPageTTransformer((RawDataTDto) rawDataTDto);
        validateTransformerAndPersist(advertisementPageTTransformer, IS_FROM_INTEGRATION_ENDPOINT);
    }

    @Override
    public List<Class<? extends AdvertisementPageTransformer>> mapStreamToTransformer(BufferedReader br) {
        try {
            CSVReader csvReader = csvReaderInit(br);
            List<String> header = getDataHeader(csvReader);

            String[] nextRecord;
            int cnt = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("<-- " + cnt++ + " -->");

                AdvertisementPageTTransformer tTransformer = new AdvertisementPageTTransformer();

                for (int c = 0; c < nextRecord.length; c++) {
                    mapItemToTransformer(header.get(c), nextRecord[c], tTransformer);
                }

                validateTransformerAndPersist(tTransformer);
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
                    tTransformer.setPageTitle(item);
                    break;
                case "Zone":
                    tTransformer.convertZone(item);
                    break;
                case "Price":
                    tTransformer.convertToPriceList(item);
                    break;
                case "NoOfRooms":
                    tTransformer.convertToNoOfRooms(item);
                    break;
                case "UsefulArea":
                    tTransformer.convertToUsefulArea(item);
                    break;
                case "BuiltSurface":
                    tTransformer.convertToBuiltSurface(item);
                    break;
                case "Partitioning":
                    tTransformer.setPartitioning(item);
                    break;
                case "Floor":
                    tTransformer.convertFloorItem(item);
                    break;
                case "NoOfBathrooms":
                    tTransformer.convertToNoOfBathrooms(item);
                    break;
                case "ConstructionYear":
                    tTransformer.convertToConstructionYear(item);
                    break;
                case "PropertyType":
                    tTransformer.setBuildingType(item);
                    break;
                case "TotalFloors":
                    tTransformer.convertToTotalFloors(item);
                    break;
                case "PageId":
                    tTransformer.setPageId(item);
                    break;
                case "LastUpdate":
                    tTransformer.convertToLastUpdated(item);
                    break;
                case "State":
                    tTransformer.setEstateState(item);
                    break;
                case "Image1":
                    tTransformer.setImage1(item);
                    break;
                case "Image2":
                    tTransformer.setImage2(item);
                    break;
                default:
                    LOG.error(String.format("The header %s could not be matched with a case", header));
                    break;
            }
        } catch (EmptyFieldException e) {
            LOG.warn(header + " field is empty");
        }
    }

}

package org.devon.app.services;

import org.devon.app.dto.RawDataDto;
import org.devon.app.dto.RawDataMDto;
import org.devon.app.dto.RawDataTDto;
import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;

import java.io.BufferedReader;
import java.util.List;

public interface IintegrationService {

    List<Class<? extends AdvertisementPageTransformer>> mapStreamToTransformer(BufferedReader br);

    Boolean checkForDuplicatesBetween(AdvertisementPage apToCheck, boolean fromIntegrationEndpoint);

    <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin (E apTransformer);

    <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E apTransformer);

    <E extends RawDataDto> void mapDtoToTransformer(E rawDataTDto);

//    void mapDtoToTransformerM(RawDataMDto rawDataMDto);
}

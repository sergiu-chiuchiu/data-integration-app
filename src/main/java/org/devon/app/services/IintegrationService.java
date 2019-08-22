package org.devon.app.services;

import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;

import java.io.BufferedReader;
import java.util.List;

public interface IintegrationService {

    List<Class<? extends AdvertisementPageTransformer>> mapStreamToEntities(BufferedReader br);

    Boolean checkForDuplicatesBetween(AdvertisementPage apToCheck);

    <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin (E apTransformer);

    <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E apTransformer);

}

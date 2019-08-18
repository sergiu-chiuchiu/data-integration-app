package org.devon.app.services.impl;

import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.services.IintegrationService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;

@Service
public class IntegrationServiceT  { //TODO

    //TODO
    public List<Class<? extends AdvertisementPageTransformer>> mapStreamToTransformer(BufferedReader br) {
        return null;
    }
}

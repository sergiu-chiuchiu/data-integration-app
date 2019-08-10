package org.devon.app.services.impl;

import org.devon.app.entities.decorators.AdvertisementPageDecorator;
import org.devon.app.services.IintegrationService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;

@Service
public class IntegrationServiceT implements IintegrationService {

    @Override
    public List<Class<? extends AdvertisementPageDecorator>> mapStreamToTransformer(BufferedReader br) {
        return null;
    }
}

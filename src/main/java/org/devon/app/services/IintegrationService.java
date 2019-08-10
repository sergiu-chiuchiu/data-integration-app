package org.devon.app.services;

import org.devon.app.entities.decorators.AdvertisementPageDecorator;

import java.io.BufferedReader;
import java.util.List;

public interface IintegrationService {

    List<Class<? extends AdvertisementPageDecorator>> mapStreamToTransformer(BufferedReader br);



}

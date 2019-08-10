package org.devon.app.services.impl;

import org.devon.app.entities.decorators.AdvertisementPageDecorator;
import org.devon.app.services.IintegrationService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Service
public class IntegrationServiceM implements IintegrationService {

    @Override
    public List<Class<? extends AdvertisementPageDecorator>> mapStreamToTransformer(BufferedReader br) {
        try {
            // get header
            System.out.println(br.readLine());


            // map csv data to transformer

//            while (br.ready()) {
//                br.readLine();
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

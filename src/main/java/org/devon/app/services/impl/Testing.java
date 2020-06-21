package org.devon.app.services.impl;

import org.devon.app.repositories.AdvertisementPageRepository;
import org.devon.app.services.Test;
import org.springframework.stereotype.Service;

@Service
public class Testing implements Test {
    final AdvertisementPageRepository advertisementPageRepository;

    public Testing(AdvertisementPageRepository advertisementPageRepository) {
        this.advertisementPageRepository = advertisementPageRepository;
    }

    @Override
    public void testing() {
        System.out.println("fdsfds");
        advertisementPageRepository.findAll();
    }
}

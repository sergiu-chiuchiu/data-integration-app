package org.devon.app.controller;


import org.devon.app.dto.RawDataMDto;
import org.devon.app.dto.RawDataTDto;
import org.devon.app.services.IintegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/data-integration")
public class DataIntegrationController {

    private final IintegrationService integrationServiceM;
    private final IintegrationService integrationServiceT;

    @Autowired
    public DataIntegrationController(
            @Qualifier("integrationServiceM") IintegrationService integrationServiceM,
            @Qualifier("integrationServiceT") IintegrationService integrationServiceT
    ) {
        this.integrationServiceM = integrationServiceM;
        this.integrationServiceT = integrationServiceT;
    }

    @PostMapping(path = "/t")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCollectedDataT(@RequestBody List<RawDataTDto> rawDataTDtoList) {
        RawDataTDto rawDataTDto = rawDataTDtoList.get(0);
        System.out.println("Post endpoint T called. Page title: " + rawDataTDto.toString());
        integrationServiceT.mapDtoToTransformer(rawDataTDto);
    }

    @PostMapping(path = "/m")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCollectedDataM(@RequestBody List<RawDataMDto> rawDataMDtoList) {
        RawDataMDto rawDataMDto = rawDataMDtoList.get(0);
        System.out.println("Post endpoint T called. Page title: " + rawDataMDto.getPageTitle());
        integrationServiceM.mapDtoToTransformer(rawDataMDto);
    }

}

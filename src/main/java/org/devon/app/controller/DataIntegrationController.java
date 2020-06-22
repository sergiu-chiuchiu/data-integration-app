package org.devon.app.controller;


import org.devon.app.dto.RawDataTDto;
import org.devon.app.mapper.TransformerMapper;
import org.devon.app.services.IintegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/data-integration")
public class DataIntegrationController {

    TransformerMapper transformerMapper;
    IintegrationService iintegrationService;

    @Autowired
    public DataIntegrationController(
            TransformerMapper transformerMapper,
            @Qualifier("integrationServiceT") IintegrationService iintegrationService
    ) {
        this.transformerMapper = transformerMapper;
        this.iintegrationService = iintegrationService;
    }

    @PostMapping(path = "/t")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCollectedDataSt(@RequestBody List<RawDataTDto> rawDataTDtoList) {
        RawDataTDto rawDataTDto = rawDataTDtoList.get(0);
        System.out.println("Post endpoint T called. Page title: " + rawDataTDto.getPageTitle());
        iintegrationService.mapDtoToTransformer(rawDataTDto);
    }

}

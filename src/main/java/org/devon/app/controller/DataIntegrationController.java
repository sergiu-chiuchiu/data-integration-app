package org.devon.app.controller;


import org.devon.app.dto.RawDataStDto;
import org.devon.app.dto.TestDto;
import org.devon.app.services.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/data-integration")
public class DataIntegrationController {

    Test test;

    @Autowired
    public DataIntegrationController(Test thetest) {
        this.test = thetest;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String test() {
        System.out.println("Get called");
        return "Hello";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCollectedDataSt(@RequestBody List<RawDataStDto> rawDataStDtoList) {
        RawDataStDto rawDataStDto = rawDataStDtoList.get(0);
        System.out.println("Post endpoint caleld!! " + rawDataStDto.getPageTitle());

    }

}

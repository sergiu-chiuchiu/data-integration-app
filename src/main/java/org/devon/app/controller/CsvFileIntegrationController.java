package org.devon.app.controller;

import org.devon.app.FlowExecutionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.devon.app.utils.Constants.DATA_SOURCE_T;

@RestController
@RequestMapping(value = "/api/csv-integration")
public class CsvFileIntegrationController {

    FlowExecutionManager flowExecutionManager;

    @Autowired
    CsvFileIntegrationController(FlowExecutionManager flowExecutionManager) {
        this.flowExecutionManager = flowExecutionManager;
    }

    @GetMapping(path = "/t")
    public void integrateCsvFile() {
        System.out.println("Endpoint /api/csv-integration/t  called.");
        flowExecutionManager.setDataSource(DATA_SOURCE_T);
        flowExecutionManager.execute();
    }


}

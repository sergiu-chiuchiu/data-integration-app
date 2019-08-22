package org.devon.app;

import org.devon.app.services.IintegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class FlowExecutionManager {

//    @Qualifier("integrationServiceM")
    @Qualifier("integrationServiceT")
    @Autowired
    private IintegrationService integrationService;

    public void execute() {
        BufferedReader br;
        try {
//        File tstFile = new File("../../UiPathProjects/WebCrawlerIm/myData.csv");
            File tstFile = new File("../../UiPathProjects/WebCrawlerSt/myDataSt.csv");
            br = new BufferedReader(new FileReader(tstFile));
            integrationService.mapStreamToEntities(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}



package org.devon.app;

import org.devon.app.services.IintegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FlowExecutionManager {

    @Qualifier("integrationServiceM")
    @Autowired
    private IintegrationService integrationService;

    public void execute() {
        File tstFile = new File("../../UiPathProjects/WebCrawlerIm/myData.csv");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(tstFile));
            integrationService.mapStreamToTransformer(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}



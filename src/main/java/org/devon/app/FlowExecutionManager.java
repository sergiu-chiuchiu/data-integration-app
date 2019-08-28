package org.devon.app;

import org.devon.app.services.IintegrationService;
import org.devon.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class FlowExecutionManager {

    private static Logger LOG = LoggerFactory
            .getLogger(FlowExecutionManager.class);
    private String dataSource;

    @Qualifier("integrationServiceM")
    @Autowired
    private IintegrationService integrationServiceM;

    @Qualifier("integrationServiceT")
    @Autowired
    private IintegrationService integrationServiceT;

    public void execute() {
        BufferedReader br;
        File dataFileCSV;
        try {
            switch (dataSource) {
                case Constants.DATA_SOURCE_M:
                    dataFileCSV = new File("myData.csv");
                    br = new BufferedReader(new FileReader(dataFileCSV));
                    integrationServiceM.mapStreamToEntities(br);
                    break;
                case Constants.DATA_SOURCE_T:
                    dataFileCSV = new File("myDataSt.csv");
                    br = new BufferedReader(new FileReader(dataFileCSV));
                    integrationServiceT.mapStreamToEntities(br);
                    break;
                default:
                    LOG.error("Unknown data source type. Cannot run the " +
                            "integration. Process aborted...");
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSource() {
        return dataSource;
    }
}
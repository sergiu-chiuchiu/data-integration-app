package org.devon.app;

import org.devon.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataIntegrationApplication implements CommandLineRunner {

    @Autowired
    FlowExecutionManager executionManager;
    private static Logger LOG = LoggerFactory
            .getLogger(DataIntegrationApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(DataIntegrationApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
        executionManager.setDataSource(Constants.DATA_SOURCE_M);
        executionManager.execute();
    }

}

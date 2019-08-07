package org.devon.app;

import org.devon.app.entities.Test;
import org.devon.app.repositories.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DataIntegrationApplication implements CommandLineRunner
{
    private static Logger LOG = LoggerFactory
            .getLogger(DataIntegrationApplication.class);
    @Autowired
    private TestRepository tr;

    public static void main( String[] args )
    {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(DataIntegrationApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        test();
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }

    public void test() {
        LOG.info("STARTING TEST");

        Test t = new Test();
        t.setTest("Hello World");
        t.setNo(1234);

        tr.save(t);

        List<Test> testList = tr.findAll();

        for (Test test : testList) {
            System.out.println(test.getNo() + " " + test.getTest() + " " + test.getId());
        }

        LOG.info("END TEST");
    }



}

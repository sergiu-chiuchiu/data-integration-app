package org.devon.app;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleInteractions {

    public Boolean askForDuplicatePersistence() {
        System.out.println("Would you still like to persist the new record? y/n");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        try {
            Boolean needAnswer = true;
            while (needAnswer) {
                String name = reader.readLine();
                if (name.trim().equalsIgnoreCase("y")) {
                    needAnswer = false;
                    return true;
                } else if (name.trim().equalsIgnoreCase("n")) {

                    return needAnswer = false;
                } else {
                    // request again the answer
                    System.out.println("Answer not understood. Would you still like to persist the new record? y/n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

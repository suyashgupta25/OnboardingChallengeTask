package de.appsfactory.documentationapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DocumentationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentationAppApplication.class, args);
    }

}

package de.appsfactory.userportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserPortalApplication.class, args);
    }
}

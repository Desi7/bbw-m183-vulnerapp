package ch.bbw.m183.vulnerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VulnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VulnerApplication.class, args);
    }

}

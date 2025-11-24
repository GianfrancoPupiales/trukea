package com.apirip.trukea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrukeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrukeaApplication.class, args);
    }

}

package com.ancient.projectab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectAbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAbApplication.class, args);
    }

}

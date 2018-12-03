package com.zhou.transactionuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransactionUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionUserApplication.class, args);
    }
}

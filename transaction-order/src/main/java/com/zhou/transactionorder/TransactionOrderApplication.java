package com.zhou.transactionorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransactionOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionOrderApplication.class, args);
    }
}

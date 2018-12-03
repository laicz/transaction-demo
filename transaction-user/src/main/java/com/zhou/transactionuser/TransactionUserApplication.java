package com.zhou.transactionuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients     //可以使用feign去访问其他的项目
public class TransactionUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionUserApplication.class, args);
    }
}

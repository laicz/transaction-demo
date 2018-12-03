package com.zhou.springgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient//注册服务注解
@EnableZuulProxy//开启网关
@EnableHystrixDashboard //记录运行时间和请求次数
public class SpringGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayApplication.class, args);
    }
}

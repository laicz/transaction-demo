/**
 * Date:     2018/12/321:06
 * AUTHOR:   Administrator
 */
package com.zhou.transactionuser.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2018/12/3  21:06
 * created by zhoumb
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = "/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/hystrix")
    @HystrixCommand(defaultFallback = "is error ")
    public String hystrix() {
        int i = 10 / 0;
        return "hystrix";
    }
}

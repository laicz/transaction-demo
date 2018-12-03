/**
 * Date:     2018/11/2821:37
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2018/11/28  21:37
 * created by zhoumb
 */
@RestController
@RequestMapping(value = "/api/home")
public class HomeResourceController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }
}

/**
 * Date:     2018/12/522:16
 * AUTHOR:   Administrator
 */
package com.zhou.springdtxdbdb.web;

import com.zhou.springdtxdbdb.entity.Order;
import com.zhou.springdtxdbdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 2018/12/5  22:16
 * created by zhoumb
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/order")
    public void createOrder(Order order) {
        customerService.createOrder(order);
    }


    @GetMapping(value = "/userInfo")
    public Map userInfo(Long customerId) {
        return customerService.userInfo(customerId);
    }
}

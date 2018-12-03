/**
 * Date:     2018/12/320:55
 * AUTHOR:   Administrator
 */
package com.zhou.transactionuser.web;

import com.zhou.transactionuser.entity.Customer;
import com.zhou.transactionuser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2018/12/3  20:55
 * created by zhoumb
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getAll")
    public List<Customer> getAll(){
        return customerService.getAll();
    }


}

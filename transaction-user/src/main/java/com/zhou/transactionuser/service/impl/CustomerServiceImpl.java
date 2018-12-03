/**
 * Date:     2018/12/320:57
 * AUTHOR:   Administrator
 */
package com.zhou.transactionuser.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zhou.transactionuser.entity.Customer;
import com.zhou.transactionuser.repository.CustomerRepository;
import com.zhou.transactionuser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018/12/3  20:57
 * created by zhoumb
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}

/**
 * Date:     2018/11/2923:01
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc.web;

import com.zhou.distributedtransaction.test_001_jdbc.entity.Customer;
import com.zhou.distributedtransaction.test_001_jdbc.jms.ActiveMqDestination;
import com.zhou.distributedtransaction.test_001_jdbc.repository.CustomerRepository;
import com.zhou.distributedtransaction.test_001_jdbc.service.CustomerServiceInAnnotation;
import com.zhou.distributedtransaction.test_001_jdbc.service.CustomerServiceInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2018/11/29  23:01
 * created by zhoumb
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceInCode customerServiceInCode;
    @Autowired
    private CustomerServiceInAnnotation customerServiceInAnnotation;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/annotation")
    public Customer annotation(@RequestBody Customer customer) {
        return customerServiceInAnnotation.create(customer);
    }

    @PostMapping(value = "/code")
    public Customer code(@RequestBody Customer customer) {
        return customerServiceInCode.create(customer);
    }

    @PostMapping(value = "/message/annotation")
    @Transactional
    public void createCustomerByListenerAnnotation(@RequestParam String name) {
        jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE1, name);
    }

    @PostMapping(value = "/message/code")
    @Transactional
    public void createCustomerByListenerCode(@RequestParam String name) {
        jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE2, name);
    }

    @GetMapping(value = "/customers")
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

}

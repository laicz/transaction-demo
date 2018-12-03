/**
 * Date:     2018/11/2921:43
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc.service;

import com.alibaba.fastjson.JSON;
import com.zhou.distributedtransaction.test_001_jdbc.entity.Customer;
import com.zhou.distributedtransaction.test_001_jdbc.jms.ActiveMqDestination;
import com.zhou.distributedtransaction.test_001_jdbc.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用注解方式的事务控制
 * 2018/11/29  21:43
 * created by zhoumb
 */
@Service
public class CustomerServiceInAnnotation {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceInAnnotation.class);
    private static final String ANNOTATION_TAG = "annotation:";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        logger.info("customerService in annotation create customer:{}", JSON.toJSONString(customer));
        if (customer.getId() != null) {
            throw new RuntimeException("customer:" + customer.getId() + "已经存在");
        }
        customer.setUsername(ANNOTATION_TAG + customer.getUsername());
        //将消息发送至队列中
        jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE_REPLY, customer.getUsername());
        return customerRepository.save(customer);
    }

    @JmsListener(destination = ActiveMqDestination.CUSTOMER_CREATE1)
    //消费方法不能有返回值
    public void createByListener(String name) {
        logger.info("customerService in annotation by listener customer:" + name);
        Customer customer = new Customer();
        customer.setUsername("Annotation:" + name);
        customer.setPassword("111111");
        customer.setRole("active");

        customerRepository.save(customer);
        jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE_REPLY, customer.getUsername());
    }
}

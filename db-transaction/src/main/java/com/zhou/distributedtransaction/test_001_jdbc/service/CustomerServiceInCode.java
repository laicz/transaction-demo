/**
 * Date:     2018/11/2922:41
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 代码方式使用spring事务
 * 2018/11/29  22:41
 * created by zhoumb
 */
@Service
public class CustomerServiceInCode {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceInCode.class);
    private static final String ANNOTATION_TAG = "code:";

    @Autowired
    private CustomerRepository customerRepository;
    //注入一个事务管理器
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private JmsTemplate jmsTemplate;

    public Customer create(Customer customer) {
        logger.info("customerService in code create customer:{}", JSON.toJSONString(customer));
        if (customer.getId() != null) {
            throw new RuntimeException("customer:" + customer.getId() + "已经存在");
        }
        //实例化一个事务定义(使用默认的事务定义)
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        transactionDefinition.setTimeout(15);
        //通过管理器获取一个事务
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);

        customer.setUsername(ANNOTATION_TAG + customer.getUsername());
        try {
            //保存(在JPA中，调用save方法会默认的开启一个事务，如果事务存在使用现在存在的事务)
            Customer save = customerRepository.save(customer);
            //提交事务
            platformTransactionManager.commit(transaction);
            //发送activeMq消息
            jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE_REPLY, customer.getUsername());
            return save;
        } catch (Exception e) {
            //事务回滚
            platformTransactionManager.rollback(transaction);
            throw new RuntimeException("保存失败");
        }

    }

    @JmsListener(destination = ActiveMqDestination.CUSTOMER_CREATE2)
    public void createListener(String name) {
        logger.info("customerService message in code create customer:{}", JSON.toJSONString(name));
        //实例化一个事务定义(使用默认的事务定义)
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        transactionDefinition.setTimeout(15);
        //通过管理器获取一个事务
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        Customer customer = new Customer();
        customer.setUsername(name);
        customer.setPassword("111111");
        customer.setRole("create By code listener");
        try {
            //保存(在JPA中，调用save方法会默认的开启一个事务，如果事务存在使用现在存在的事务)
            Customer save = customerRepository.save(customer);
            //提交事务
            platformTransactionManager.commit(transaction);
            //发送activeMq消息
            jmsTemplate.convertAndSend(ActiveMqDestination.CUSTOMER_CREATE_REPLY, customer.getUsername());
        } catch (Exception e) {
            //事务回滚
            platformTransactionManager.rollback(transaction);
            throw new RuntimeException("保存失败");
        }

    }
}

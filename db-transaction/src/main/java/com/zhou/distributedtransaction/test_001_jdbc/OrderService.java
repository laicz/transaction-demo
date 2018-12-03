/**
 * Date:     2018/11/2921:08
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 代码的方式实现事务管理
 * 2018/11/29  21:08
 * created by zhoumb
 */
@Service
public class OrderService {
    //注入事务管理器
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 代码实现事务
     * @throws TransactionException
     */
    void buyTicket() throws TransactionException {
        //创建一个事务定义
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //设置事务传播机制
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //事务管理器获取根据事务定义获取一个事务
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        //事务提交
        platformTransactionManager.commit(transactionStatus);
    }

    /**
     * 声明式实现事务
     */
    @Transactional()
    void buyTicket1(){

    }
}

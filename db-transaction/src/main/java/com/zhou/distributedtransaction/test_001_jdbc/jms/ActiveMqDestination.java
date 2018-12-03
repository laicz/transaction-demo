/**
 * Date:     2018/12/122:03
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc.jms;

/**
 * activeMq 的destination
 * 2018/12/1  22:03
 * created by zhoumb
 */
public final class ActiveMqDestination {
    /**
     * 创建用户
     */
    public static final String CUSTOMER_CREATE1 = "customer:msg1:new";
    /**
     * 创建用户
     */
    public static final String CUSTOMER_CREATE2 = "customer:msg2:new";
    /**
     * 再次创建用户
     */
    public static final String CUSTOMER_CREATE_REPLY = "customer:msg:reply";


}

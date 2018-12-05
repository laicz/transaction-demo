/**
 * Date:     2018/12/521:40
 * AUTHOR:   Administrator
 */
package com.zhou.springdtxdbdb.service;

import com.zhou.springdtxdbdb.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2018/12/5  21:40
 * created by zhoumb
 */
@Service
public class CustomerService {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;

    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    private static final String SQL_UPDATE_DEPOSIT = "UPDATE customer SET deposit = deposit - ? WHERE id = ? ";
    private static final String SQL_CREATE_ORDER = "INSERT INTO customer_order(customerId,title,amount) VALUE(?,?,?)";

    /**
     * 使用Transactional在一个方法中，会使用设施默认的连接池的事务
     * 并不能早证两个事务的一致性
     * @param order
     */
    @Transactional
    public void createOrder(Order order) {
        userJdbcTemplate.update(SQL_UPDATE_DEPOSIT, order.getAmount(), order.getCustomerId());
        if (order.getTitle().contains("error1")) {
            throw new RuntimeException("Error1");
        }
        orderJdbcTemplate.update(SQL_CREATE_ORDER, order.getCustomerId(), order.getTitle(), order.getAmount());
        if (order.getTitle().contains("error2")) {
            throw new RuntimeException("Error2");
        }
    }

    public Map userInfo(Long customerId) {
        Map<String, Object> customerMap = userJdbcTemplate.queryForMap("SELECT * FROM customer");
        List<Map<String, Object>> orders = orderJdbcTemplate.queryForList("SELECT * FROM customer_order");

        Map result = new HashMap();
        result.put("customer", customerMap);
        result.put("orders", orders);
        return result;
    }
}

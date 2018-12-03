/**
 * Date:     2018/11/2921:40
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc.repository;

import com.zhou.distributedtransaction.test_001_jdbc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2018/11/29  21:40
 * created by zhoumb
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}

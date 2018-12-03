/**
 * Date:     2018/12/320:58
 * AUTHOR:   Administrator
 */
package com.zhou.transactionuser.repository;

import com.zhou.transactionuser.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2018/12/3  20:58
 * created by zhoumb
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

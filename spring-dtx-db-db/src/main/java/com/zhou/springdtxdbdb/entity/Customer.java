/**
 * Date:     2018/12/423:38
 * AUTHOR:   Administrator
 */
package com.zhou.springdtxdbdb.entity;

/**
 * 2018/12/4  23:38
 * created by zhoumb
 */
public class Customer {
    private Long id;
    private String username;
    private Integer deposit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
}

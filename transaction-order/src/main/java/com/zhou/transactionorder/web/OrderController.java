/**
 * Date:     2018/12/321:30
 * AUTHOR:   Administrator
 */
package com.zhou.transactionorder.web;

import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 2018/12/3  21:30
 * created by zhoumb
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @GetMapping(value = "/my")
    public Map getMyInfo() {
        return Maps.newHashMap();
    }
}

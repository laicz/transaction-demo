/**
 * Date:     2018/12/322:58
 * AUTHOR:   Administrator
 */
package com.zhou.service;

import com.zhou.dto.OrderDTO;

/**
 * 共同组建  使用同一个接口
 * 2018/12/3  22:58
 * created by zhoumb
 */
public interface IOrderService {
    OrderDTO getMyOrder(Long id);
}

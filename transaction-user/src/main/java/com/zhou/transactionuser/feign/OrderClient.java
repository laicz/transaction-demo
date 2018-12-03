/**
 * Date:     2018/12/322:43
 * AUTHOR:   Administrator
 */
package com.zhou.transactionuser.feign;

import com.zhou.dto.OrderDTO;
import com.zhou.service.IOrderService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 访问order的feign
 * 继承共同组件 这样强制使被调用的地方返回值是一致的
 * 2018/12/3  22:43
 * created by zhoumb
 */
//指定服务Id及接口前缀
@FeignClient(value = "order", path = "/api/order")
public interface OrderClient extends IOrderService {


    @GetMapping("/my")
    OrderDTO getMyOrder(Long id);
}

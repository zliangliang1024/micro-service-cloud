package com.yao0er4.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 实现PaymentHystrixFeignClientService  统一为接口里面的方法进行异常处理
 */
@Component
public class PaymentFallbackService implements PaymentHystrixFeignClientService{


    /**
     * 针对 paymentInfo_OK 的 异常处理
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "[ok]服务调用失败，提示来自：cloud-consumer-feign-order84";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "[timeout]服务调用失败，提示来自：cloud-consumer-feign-order84";
    }


}

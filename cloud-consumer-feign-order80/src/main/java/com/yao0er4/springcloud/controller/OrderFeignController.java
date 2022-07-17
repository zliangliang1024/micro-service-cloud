package com.yao0er4.springcloud.controller;

import com.yao0er4.springcloud.entities.CommonResult;
import com.yao0er4.springcloud.entities.PaymentEntity;
import com.yao0er4.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "consumer/payment/feign/timeout")
    public Integer timeout() {
        return paymentFeignService.timeoutTest();
    }
}

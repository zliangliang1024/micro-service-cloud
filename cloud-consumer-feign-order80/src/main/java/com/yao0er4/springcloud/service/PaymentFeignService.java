package com.yao0er4.springcloud.service;

import com.yao0er4.springcloud.entities.CommonResult;
import com.yao0er4.springcloud.entities.PaymentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("payment/get/{id}")
    CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout-test")
    int timeoutTest();
}

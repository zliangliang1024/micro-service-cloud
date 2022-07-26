package com.yao0er4.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yao0er4.springcloud.service.PaymentHystrixFeignClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order/consumer")
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixFeignClientService feignClientService;

    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return feignClientService.paymentInfo_OK(id);
    }

    @GetMapping("payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return feignClientService.paymentInfo_Timeout(id);
    }

    public String paymentInfoTimeoutFallback(@PathVariable("id") Integer id) {
        return "消费者，服务提供者繁忙，或者系统内部出错，请稍后重试";
    }
}

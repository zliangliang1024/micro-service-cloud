package com.yao0er4.springcloud.controller;

import com.yao0er4.springcloud.entities.CommonResult;
import com.yao0er4.springcloud.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer/payment")
public class OrderController {


    public static final String PAYMENT_SERVER_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    public CommonResult<PaymentEntity> create(PaymentEntity payment) {
        return restTemplate.postForObject(PAYMENT_SERVER_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("get/{id}")
    public CommonResult<PaymentEntity> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/payment/get/" + id, CommonResult.class, id);

    }
}
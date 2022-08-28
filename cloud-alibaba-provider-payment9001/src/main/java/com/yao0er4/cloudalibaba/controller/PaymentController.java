package com.yao0er4.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("{id}")
    private String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry,serverPort:" + serverPort + "\t id:" + id;
    }
}

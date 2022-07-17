package com.yao0er4.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentMain8004.class, args);
    }
}

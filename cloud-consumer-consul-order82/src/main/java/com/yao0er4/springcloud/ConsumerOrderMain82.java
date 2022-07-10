package com.yao0er4.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderMain82 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain82.class, args);
    }
}

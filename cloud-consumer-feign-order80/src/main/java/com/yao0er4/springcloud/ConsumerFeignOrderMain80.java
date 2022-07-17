package com.yao0er4.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignOrderMain80 {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerFeignOrderMain80.class, args);
    }
}

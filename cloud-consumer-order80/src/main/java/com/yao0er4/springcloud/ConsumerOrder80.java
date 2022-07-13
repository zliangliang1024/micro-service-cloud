package com.yao0er4.springcloud;

import com.yao0er4.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class ConsumerOrder80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80.class, args);
    }
}

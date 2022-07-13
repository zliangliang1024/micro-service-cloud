package com.yao0er4.springcloud.controller;

import com.yao0er4.springcloud.lb.LoadBalancer;
import com.yao0er4.springcloud.entities.CommonResult;
import com.yao0er4.springcloud.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer/payment")
public class OrderController {


    //    public static final String PAYMENT_SERVER_URL = "http://localhost:8001";
    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_SERVER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("getForEntity/{id}")
    public CommonResult<PaymentEntity> getForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_SERVER_URL + "/payment/get/" + id, CommonResult.class, id);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("服务：" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/lb")
    public Integer getPaymentLB() {

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", Integer.class);
    }
}
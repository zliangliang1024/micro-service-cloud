package com.yao0er4.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "paymentGlobalFallback")
public class PaymentService {

    /**
     * 正常访问
     *
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id;
    }

    /**
     * 超时访问，演示降级
     *
     * @param id
     * @return
     */
    @HystrixCommand // 没有特别指定就用默认的
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",
//            commandProperties =
//                    // 3秒得不到返回，就会调用服务降级方法
//                    // https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.thread.timeoutInMilliseconds
//                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String paymentInfo_Timeout(Integer id) {
        int second = 5;
//        int age = 10 / 0;  // 抛异常后也会直接执行 fallback 指定的 方法
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_Timeout,id:" + id + "\t耗时:" + second;
    }

    /**
     * 参数必须和需要降级的方法一致
     *
     * @param id
     * @return
     */
    public String paymentInfoTimeoutHandler(Integer id) {
        return "提供者：调用当前接口超时或异常：\t 线程：" + Thread.currentThread().getName();
    }

    public String paymentGlobalFallback() {
        return "提供者：【全局】调用当前接口超时或异常";
    }

    // 服务熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    // 开启断路器
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    // 请求次数超过了峰值，熔断器会从关闭状态转变为半开状态
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                    // 时间范围
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    // 失败率达到多少后跳闸
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
            }
    )
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t 调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id 不能为负数，请稍后再试：id:" + id;
    }
}

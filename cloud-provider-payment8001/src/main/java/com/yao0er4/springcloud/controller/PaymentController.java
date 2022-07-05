package com.yao0er4.springcloud.controller;

import com.yao0er4.springcloud.entities.CommonResult;
import com.yao0er4.springcloud.entities.PaymentEntity;
import com.yao0er4.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody PaymentEntity paymentEntity) {
        int result = paymentService.create(paymentEntity);
        log.info("******插入操作返回结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入成功", result);
        }
        return new CommonResult<>(400, "插入失败", null);
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> get(@PathVariable("id") Long id) {
        PaymentEntity paymentById = paymentService.getPaymentById(id);
        log.info("******查询结果：{}：", paymentById);
        if (paymentById != null) {
            return new CommonResult<>(200, "查询成功", paymentById);
        }
        return new CommonResult<>(400, "查询失败，id-[" + id + "]不存在", null);
    }
}

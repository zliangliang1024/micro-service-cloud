package com.yao0er4.springcloud.service;

import com.yao0er4.springcloud.entities.PaymentEntity;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(PaymentEntity payment);

    PaymentEntity getPaymentById(@Param("id") Long id);
}

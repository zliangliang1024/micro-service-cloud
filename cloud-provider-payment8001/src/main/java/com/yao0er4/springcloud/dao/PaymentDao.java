package com.yao0er4.springcloud.dao;

import com.yao0er4.springcloud.entities.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(PaymentEntity payment);

    PaymentEntity getPaymentById(@Param("id") Long id);
}

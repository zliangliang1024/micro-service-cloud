package com.yao0er4.springcloud.service.impl;

import com.yao0er4.springcloud.dao.PaymentDao;
import com.yao0er4.springcloud.entities.PaymentEntity;
import com.yao0er4.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(PaymentEntity payment) {
        return paymentDao.create(payment);
    }

    @Override
    public PaymentEntity getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

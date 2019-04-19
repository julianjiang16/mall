package com.sherlock.mallpayment.service.impl;

import com.sherlock.mallpayment.converter.OrderConverter;
import com.sherlock.mallpayment.enums.OrderStatusEnums;
import com.sherlock.mallpayment.facade.BankFacade;
import com.sherlock.mallpayment.mapper.order.OrderHeadMapper;
import com.sherlock.mallpayment.mapper.order.ext.OrderHeadMapperExt;
import com.sherlock.mallpayment.mapper.payment.PaymentMapper;
import com.sherlock.mallpayment.model.payment.Payment;
import com.sherlock.mallpayment.service.IPaymentService;
import com.sherlock.mallpayment.vo.OrderVO;
import com.sherlock.mallpayment.vo.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Copyright (C), 2015-2019
 * FileName: PaymentServiceImpl
 * Author:   jcj
 * Date:     2019/4/19 9:40
 * Description:
 */
@Slf4j
@Service("paymentService")
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    BankFacade bankFacade;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    OrderHeadMapperExt orderHeadMapperExt;

    @Autowired
    OrderHeadMapper orderHeadMapper;

    @Autowired
    PaymentMapper paymentMapper;


    /**
     * 功能描述: <br> 支付逻辑
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/19 10:00
     */
    @Transactional(propagation = Propagation.NEVER)
    @Override
    public void pay(OrderVO order) {

        String paymentId = transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                boolean updateFlag = 1 == orderHeadMapperExt.updateStatusByOrderIdAndStatus(order.getId(), order.getStatus());
                // 锁成功 发起支付流水
                if (updateFlag) {
                    Payment payment = new Payment();
                    payment.setId(order.getId());//假装这个是流水号
                    // 表示默认的
                    payment.setStatus(OrderStatusEnums.DEFAULT_ORDER_STATUS.getCode());
                    paymentMapper.insertSelective(payment);
                    return payment.getId();
                }
                return null;
            }
        });
        // 获取到锁
        if (StringUtils.isNotEmpty(paymentId)) {
            // 构造发送到银行方的支付订单
            OrderVO payOrder = new OrderVO();
            payOrder.setId(paymentId);
            RespEntity<Object> respEntity = bankFacade.dealMoney(payOrder);
            if (null != respEntity) {
                if (respEntity.getData().equals(OrderStatusEnums.DEFAULT_ORDER_STATUS.getCode())) {//也假装这data返回的状态 且这个状态为支付成功
                    payOrder.setStatus(OrderStatusEnums.ORDER_STATUS_SUCCESS.getCode());
                } else {
                    payOrder.setStatus(OrderStatusEnums.ORDER_STATUS_FAIL.getCode());
                }
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        orderHeadMapper.updateByPrimaryKeySelective(OrderConverter.convertVO2DO(payOrder));
                        Payment payment =  new Payment();
                        payment.setId(paymentId);
                        payment.setStatus(payOrder.getStatus());
                        paymentMapper.updateByPrimaryKeySelective(payment);
                    }
                });
            }
        }else {
            log.warn("payment not get the lock , order info : {}",order);
        }
    }
}

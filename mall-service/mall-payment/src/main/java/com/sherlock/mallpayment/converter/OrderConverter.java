package com.sherlock.mallpayment.converter;

import com.sherlock.mallpayment.model.order.OrderHead;
import com.sherlock.mallpayment.vo.OrderVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2019
 * FileName: OrderConverter
 * Author:   jcj
 * Date:     2019/4/19 10:59
 * Description:
 */
public class OrderConverter {

    public static OrderHead convertVO2DO(OrderVO order){
        OrderHead orderHead = new OrderHead();
        orderHead.setId(order.getId());
        orderHead.setStatus(order.getStatus());
        orderHead.setGmtCreate(order.getGmtCreate());
        orderHead.setGmtModified(order.getGmtModified());
        return orderHead;
    }

    public static List<OrderHead> convertVOs2DOs(List<OrderVO> orders){
        return orders.stream().map(OrderConverter::convertVO2DO).collect(Collectors.toList());
    }
}

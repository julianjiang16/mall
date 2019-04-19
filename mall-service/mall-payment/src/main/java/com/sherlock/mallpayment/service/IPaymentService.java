package com.sherlock.mallpayment.service;

import com.sherlock.mallpayment.vo.OrderVO;

/**
 * Copyright (C), 2015-2019,
 * FileName: IPaymentService
 * Author:   jcj
 * Date:     2019/4/19 9:33
 * Description:
 */
public interface IPaymentService {

    void pay(OrderVO orderVO);
}

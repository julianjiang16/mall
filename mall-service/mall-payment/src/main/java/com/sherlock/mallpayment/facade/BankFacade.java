package com.sherlock.mallpayment.facade;

import com.sherlock.mallpayment.vo.OrderVO;
import com.sherlock.mallpayment.vo.RespEntity;

/**
 * Copyright (C), 2015-2019,
 * FileName: BankFacade
 * Author:   jcj
 * Date:     2019/4/19 9:33
 * Description:
 */
public interface BankFacade<T> {

    RespEntity<T> dealMoney(OrderVO order);
}

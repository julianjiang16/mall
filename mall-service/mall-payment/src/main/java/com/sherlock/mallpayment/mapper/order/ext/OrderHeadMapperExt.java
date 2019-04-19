package com.sherlock.mallpayment.mapper.order.ext;

import org.apache.ibatis.annotations.Param;

public interface OrderHeadMapperExt {

    int updateStatusByOrderIdAndStatus(@Param("order_Id") String orderId,
                                       @Param("status")String status);
}
package com.sherlock.mallpayment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2015-2019
 * FileName: OrderVO
 * Author:   jcj
 * Date:     2019/4/19 10:16
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO implements Serializable {
    private String id;
    private Integer price;
    private String status;
    private Date gmtCreate;
    private Date gmtModified;
}

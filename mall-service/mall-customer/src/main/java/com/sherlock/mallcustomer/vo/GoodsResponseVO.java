package com.sherlock.mallcustomer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsResponseVO
 * Author:   jcj
 * Date:     2019/4/17 17:19
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponseVO {

    private String id;
    private String no;
    private String name;
    private String typeName;
    private Integer price;
    private Date gmtCreate;
    private Date gmtModified;
}

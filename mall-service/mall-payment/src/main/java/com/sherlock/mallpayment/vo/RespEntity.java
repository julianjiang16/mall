package com.sherlock.mallpayment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2015-2019
 * FileName: RespEntity
 * Author:   jcj
 * Date:     2019/4/19 9:34
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespEntity<T> {
    private T data;
}

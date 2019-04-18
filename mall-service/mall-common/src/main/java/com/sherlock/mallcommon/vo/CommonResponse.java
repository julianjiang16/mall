package com.sherlock.mallcommon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (C), 2015-2019
 * FileName: CommonResponse
 * Author:   jcj
 * Date:     2019/4/17 14:08
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

}

package com.sherlock.mallcommon.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2015-2019
 * FileName: Ignore
 * Author:   jcj
 * Date:     2019/4/17 14:09
 * Description: 忽略封装统一响应
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
}

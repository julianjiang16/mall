package com.sherlock.mallcommon.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Copyright (C), 2015-2019
 * FileName: CollectionUtils
 * Author:   jcj
 * Date:     2019/4/18 9:49
 * Description:
 */
public class CollectionUtils {

    /**
     * 功能描述: <br> 获取值或者创建一个空对象
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 10:04
     */
    public static <K,V> V getOrCreate(K key, Map<K,V> map, Supplier<V> supplier){
        return map.computeIfAbsent(key,v->supplier.get());
    }

}

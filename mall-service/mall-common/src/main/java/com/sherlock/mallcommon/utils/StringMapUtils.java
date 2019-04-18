package com.sherlock.mallcommon.utils;

import com.google.common.collect.Maps;
import com.sherlock.mallcommon.constant.CommonConstant;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Copyright (C), 2015-2018,
 * FileName: StringUtils
 * Author:   jcj
 * Date:     2018/10/30 9:55
 * Description: 字符串 map 工具类
 */
public class StringMapUtils {

    /**
     * 功能描述: <br> 解析map字符串
     *             <br> 例如：key1=value1$key2=value2
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/30 10:09
     */
    public static Map<String,String> resolveMapStr(String source){
        Map<String, String> map = Maps.newHashMap();
        if (StringUtils.isEmpty(source))
        {
            return map;
        }
        // 根据类型
        String[] pair = source.split(CommonConstant.DOLLAR_SEPARATOR_TRANSFERENCE);
        
        if (pair.length == 0)
        {
            return map;
        }
        
        // 遍历每一个key=value对
        for (String target : pair)
        {
            if (!target.contains(CommonConstant.EQUAL_MARK_SEPARATOR)){
                continue;
            }
            String[] keyValPair=target.split(CommonConstant.EQUAL_MARK_SEPARATOR);
            if (keyValPair != null && keyValPair.length == 2)
            {
                map.put(keyValPair[0],keyValPair[1]);
            }
        }
        return map;
    }
}

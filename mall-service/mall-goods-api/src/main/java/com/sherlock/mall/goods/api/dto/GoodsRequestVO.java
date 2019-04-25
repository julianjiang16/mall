package com.sherlock.mall.goods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsRequestVO
 * Author:   jcj
 * Date:     2019/4/17 17:19
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequestVO {
    private String customerId;
    private String no;
    private String typeId;
    private String name;
    private Integer start;
    private Integer rows;

    /**
     * 功能描述: <br> 校验分页参数
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 9:14
     */
    public boolean validatePageInfo(){
        if (null == this.start || null == this.rows){
            return false;
        }
        return true;
    }
}

package com.sherlock.mallgoods.converter;

import com.sherlock.mallgoods.model.goods.Goods;
import com.sherlock.mallgoods.vo.GoodsResponseVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsConverter
 * Author:   jcj
 * Date:     2019/4/17 17:31
 * Description:
 */
public class GoodsConverter {

    public static GoodsResponseVO convertDO2VO(Goods goods){
        GoodsResponseVO response = new GoodsResponseVO();
        response.setId(goods.getId());
        response.setName(goods.getName());
        response.setNo(goods.getNo());
        response.setPrice(goods.getPrice());
//        response.setTypeName();
        response.setGmtCreate(goods.getGmtCreate());
        response.setGmtModified(goods.getGmtModified());
        return response;
    }

    public static List<GoodsResponseVO> convertDOs2VOs(List<Goods> goods){
        return goods.stream().map(GoodsConverter::convertDO2VO).collect(Collectors.toList());
    }
}

package com.sherlock.mall.goods.api.service;

import com.sherlock.mall.goods.api.dto.GoodsRequestVO;
import com.sherlock.mall.goods.api.dto.GoodsResponseVO;
import com.sherlock.mallcommon.exception.MallException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright (C), 2015-2019,
 * FileName: GoodsService
 * Author:   jcj
 * Date:     2019/4/17 17:12
 * Description: 服务器、客户端共有，抽取出来，继承依赖
 */

@RequestMapping("/goods/")
public interface GoodsService {

    @GetMapping(value = "goods")
    List<GoodsResponseVO> findBatch(GoodsRequestVO requestVO) throws MallException;

    /**
     * 功能描述: <br> 获取用户购买的商品列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 15:04
     */
    @PostMapping(value = "customer/goods")
    List<GoodsResponseVO> findUserGoods(@RequestBody GoodsRequestVO requestVO, HttpServletRequest request) throws MallException;
}

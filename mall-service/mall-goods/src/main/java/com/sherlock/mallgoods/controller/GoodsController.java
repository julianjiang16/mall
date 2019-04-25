package com.sherlock.mallgoods.controller;

import com.sherlock.mall.goods.api.dto.GoodsRequestVO;
import com.sherlock.mall.goods.api.dto.GoodsResponseVO;
import com.sherlock.mall.goods.api.service.GoodsService;
import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallgoods.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsController
 * Author:   jcj
 * Date:     2019/4/18 9:19
 * Description:
 */
@RestController
@Slf4j
public class GoodsController implements GoodsService {

    @Autowired
    IGoodsService iGoodsService;

    /**
     * 功能描述: <br> 根据条件查询
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 9:20
     */
    @Override
    public List<GoodsResponseVO> findBatch(GoodsRequestVO requestVO) throws MallException {
        return iGoodsService.findListByCondition(requestVO);
    }
    /**
     * 功能描述: <br> 获取用户购买的商品列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 15:04
     */
    @Override
    public List<GoodsResponseVO> findUserGoods(GoodsRequestVO requestVO, HttpServletRequest request) throws MallException {
        return iGoodsService.findCustomerGoods(requestVO);
    }
}

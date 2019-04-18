package com.sherlock.mallgoods.controller;

import com.sherlock.mallcommon.anno.Ignore;
import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallgoods.service.IGoodsService;
import com.sherlock.mallgoods.vo.GoodsRequestVO;
import com.sherlock.mallgoods.vo.GoodsResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/goods/")
@Slf4j
public class GoodsController {

    @Autowired
    IGoodsService iGoodsService;

    /**
     * 功能描述: <br> 根据条件查询
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 9:20
     */
    @GetMapping("goods")
    public List<GoodsResponseVO> findBatch(GoodsRequestVO requestVO) throws MallException {
        return iGoodsService.findListByCondition(requestVO);
    }

    /**
     * 功能描述: <br> 获取用户购买的商品列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/18 15:04
     */
    @Ignore
    @PostMapping(value = "customer/goods")
    public List<GoodsResponseVO> findUserGoods(@RequestBody GoodsRequestVO requestVO, HttpServletRequest request) throws MallException {
        return iGoodsService.findCustomerGoods(requestVO);
    }
}

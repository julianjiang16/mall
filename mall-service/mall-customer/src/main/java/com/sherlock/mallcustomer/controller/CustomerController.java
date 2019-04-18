package com.sherlock.mallcustomer.controller;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcustomer.service.GoodsFeignService;
import com.sherlock.mallcustomer.vo.GoodsRequestVO;
import com.sherlock.mallcustomer.vo.GoodsResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: CustomerController
 * Author:   jcj
 * Date:     2019/4/18 15:01
 * Description:
 */
@RestController
public class CustomerController {

    @Autowired
    GoodsFeignService goodsFeignService;

    @PostMapping(value = "goods")
    public List<GoodsResponseVO> findGoods(@RequestBody GoodsRequestVO requestVO) throws MallException {
        return goodsFeignService.findUserGoods(requestVO);
    }
}

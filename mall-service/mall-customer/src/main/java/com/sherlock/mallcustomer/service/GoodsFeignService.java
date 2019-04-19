package com.sherlock.mallcustomer.service;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcustomer.service.fallback.GoodsFallbackHandler;
import com.sherlock.mallcustomer.vo.GoodsRequestVO;
import com.sherlock.mallcustomer.vo.GoodsResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Copyright (C), 2015-2019,
 * FileName: GoodsFeignService
 * Author:   jcj
 * Date:     2019/4/18 14:54
 * Description:
 */
@FeignClient(value = "mall-goods",fallback = GoodsFallbackHandler.class)
public interface GoodsFeignService {

    @PostMapping("mall-goods/goods/customer/goods")
    List<GoodsResponseVO> findUserGoods(@RequestBody GoodsRequestVO requestVO) throws MallException;
}

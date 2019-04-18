package com.sherlock.mallcustomer.service.fallback;

import com.google.common.collect.Lists;
import com.sherlock.mallcustomer.service.GoodsFeignService;
import com.sherlock.mallcustomer.vo.GoodsRequestVO;
import com.sherlock.mallcustomer.vo.GoodsResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsFallbackHandler
 * Author:   jcj
 * Date:     2019/4/18 15:03
 * Description:
 */
@Component
@Slf4j
public class GoodsFallbackHandler implements GoodsFeignService {
    @Override
    public List<GoodsResponseVO> findUserGoods(GoodsRequestVO requestVO) {
        log.info("service invoke error , invoke params: {}" , requestVO);
        return Lists.newArrayList();
    }
}

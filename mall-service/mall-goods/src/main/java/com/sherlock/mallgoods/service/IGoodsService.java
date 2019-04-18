package com.sherlock.mallgoods.service;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallgoods.vo.GoodsRequestVO;
import com.sherlock.mallgoods.vo.GoodsResponseVO;

import java.util.List;

/**
 * Copyright (C), 2015-2019,
 * FileName: GoodsService
 * Author:   jcj
 * Date:     2019/4/17 17:12
 * Description:
 */
public interface IGoodsService {

    GoodsResponseVO findOneByCondition(String id) throws MallException;

    List<GoodsResponseVO> findListByCondition(GoodsRequestVO requestVO) throws MallException;
}

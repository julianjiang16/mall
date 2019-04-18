package com.sherlock.mallgoods.service.impl;

import com.sherlock.mallcommon.constant.CommonConstant;
import com.sherlock.mallcommon.enums.MallErrorEnum;
import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallgoods.converter.GoodsConverter;
import com.sherlock.mallgoods.mapper.goods.GoodsMapper;
import com.sherlock.mallgoods.model.goods.Goods;
import com.sherlock.mallgoods.model.goods.GoodsExample;
import com.sherlock.mallgoods.service.IGoodsService;
import com.sherlock.mallgoods.vo.GoodsRequestVO;
import com.sherlock.mallgoods.vo.GoodsResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsServiceImpl
 * Author:   jcj
 * Date:     2019/4/17 17:12
 * Description:
 */
@Slf4j
@Service("iGoodsService")
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public GoodsResponseVO findOneByCondition(String id) throws MallException {
        if (StringUtils.isEmpty(id)){
            throw new MallException(MallErrorEnum.ERROR_REQUEST_PARAMS);
        }
        Goods goods = goodsMapper.selectByPrimaryKey(id);

        return GoodsConverter.convertDO2VO(goods);
    }

    @Override
    public List<GoodsResponseVO> findListByCondition(GoodsRequestVO requestVO) throws MallException {
        if (!requestVO.validatePageInfo()){
            throw new MallException(MallErrorEnum.ERROR_REQUEST_PARAMS);
        }
        GoodsExample example = new GoodsExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(requestVO.getStart());
        example.setRows(requestVO.getRows());
        GoodsExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotEmpty(requestVO.getName())){
            c.andNameLike(requestVO.getName() + CommonConstant.PERCENT_SIGN);
        }
        if (StringUtils.isNotEmpty(requestVO.getNo())){
            c.andNoEqualTo(requestVO.getNo());
        }
        if (StringUtils.isNotEmpty(requestVO.getTypeId())){
            c.andTypeIdEqualTo(requestVO.getTypeId());
        }
        List<Goods> goods = goodsMapper.selectByExample(example);

        return GoodsConverter.convertDOs2VOs(goods);
    }

    @Override
    public List<GoodsResponseVO> findCustomerGoods(GoodsRequestVO requestVO) throws MallException {
        if (!requestVO.validatePageInfo()){
            throw new MallException(MallErrorEnum.ERROR_REQUEST_PARAMS);
        }
        GoodsExample example = new GoodsExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(requestVO.getStart());
        example.setRows(requestVO.getRows());
        List<Goods> goods = goodsMapper.selectByExample(example);
        // 没实现查询customer_id
        return GoodsConverter.convertDOs2VOs(goods);
    }
}

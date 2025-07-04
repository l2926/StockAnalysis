package com.example.springboot.market.mapper;

import com.example.springboot.market.vo.req.MarketReq;
import com.example.springboot.market.vo.resp.SubordinateResp;
import com.example.springboot.market.vo.vo.AssetCenterVo;
import com.example.springboot.market.vo.vo.DailyVo;
import com.example.springboot.market.vo.vo.FinanceCenterVo;
import com.example.springboot.market.vo.vo.MoneyFlowVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lizijian
 * @desc 价格查询mapper
 * @date 2024-09-19
 */
@Mapper
public interface MarketMapper {
    List<DailyVo> selectDaily(MarketReq marketReq);

    List<SubordinateResp> selectSubordinate(MarketReq marketReq);
    List<FinanceCenterVo> selectFinanceCenter(MarketReq marketReq);
    List<AssetCenterVo> selectAssetCenter(MarketReq marketReq);
    MoneyFlowVo selectMoneyFlow(MarketReq marketReq);
}

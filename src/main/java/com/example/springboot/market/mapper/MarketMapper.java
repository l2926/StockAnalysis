package com.example.springboot.market.mapper;

import com.example.springboot.industry.vo.resp.*;
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
    /**
     * 日线行情
     * @param marketReq
     * @return
     */
    List<DailyVo> selectDaily(MarketReq marketReq);

    /**
     * 股票所属
     * @param marketReq
     * @return
     */
    List<SubordinateResp> selectSubordinate(MarketReq marketReq);

    /**
     * 财务中心
     * @param marketReq
     * @return
     */
    List<FinanceCenterVo> selectFinanceCenter(MarketReq marketReq);

    /**
     * 资产中心
     * @param marketReq
     * @return
     */
    List<AssetCenterVo> selectAssetCenter(MarketReq marketReq);

    /**
     * 资金流向
     * @param marketReq
     * @return
     */
    MoneyFlowVo selectMoneyFlow(MarketReq marketReq);
    /**
     * 主营业务1
     */
    List<FinaMainResp> selectFinaMain(MarketReq marketReq);

    /**
     * 主营业务2
     * @param marketReq
     * @return
     */
    List<FinaMain2Resp> selectFinaMain2(MarketReq marketReq);
    /**
     * 主营业务3
     * @param marketReq
     * @return
     */
    List<FinaMain3Resp> selectFinaMain3(MarketReq marketReq);
    /**
     * 十大股东
     */
    List<TopHoldResp> selectTopHold(MarketReq marketReq);
    /**
     * 公司信心
     * @param marketReq
     * @return
     */
    List<CompanyInfoResp> selectCompanyInfo(MarketReq marketReq);
}

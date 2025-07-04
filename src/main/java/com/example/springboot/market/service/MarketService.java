package com.example.springboot.market.service;

import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import com.example.springboot.market.vo.req.MarketReq;
import com.example.springboot.market.vo.resp.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lizijian
 * @desc 市场 服务层
 * @date 2024-09-28
 */

public interface MarketService {
    /**
     * 获取日线
     * @param marketReq
     * @return
     */
    DailyResp getDaily(MarketReq marketReq);
    /**
     * 获取所属行业
     * @param marketReq
     * @return
     */
    List<SubordinateResp> getSubordinate(MarketReq marketReq);
    /**
     * 获取财务概览数据
     */
    FinanceCenterResp getFianceCenter(MarketReq marketReq);
    /**
     * 获取资产概览数据
     */
    AssetCenterResp getAssetCenter(MarketReq marketReq);
    /**
     * 获取log日线行情
     */
    LogDailyResp getLogDaily(MarketReq marketReq);
    /**
     * 获取某股票、某日的资金流向数据
     */
    MoneyFlowResp getMoneyFlow(MarketReq marketReq);
    /**
     * 获取该股票的主营业务2
     */
    List<FinaMain2Resp> getFinaMain2(MarketReq marketReq);
    /**
     * 获取该股票的主营业务3
     */
    List<FinaMain3Resp> getFinaMain3(MarketReq marketReq);
    /**
     * 获取该股票的公司信息
     */
    List<CompanyInfoResp> getCompanyInfo(MarketReq marketReq);
}

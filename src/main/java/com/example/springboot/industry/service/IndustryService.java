package com.example.springboot.industry.service;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TwoDaysDifferResp;
import com.example.springboot.industry.vo.req.IndustryReq;
import com.example.springboot.industry.vo.resp.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: lizijian
 * @desc: 行业Service
 * @date: 2024-09-25
 */
public interface IndustryService {
    /**
     * 获取行业基本面数据
     */
    List<FundmentalResp> getFundmental(IndustryReq industryReq);
    /**
     * 获取行业主营业务数据
     */
    List<FinaMainResp> getFinaMain(IndustryReq industryReq);
    /**
     * 获取行业前五大股东数据
     */
    List<TopHoldResp> getTopHold(IndustryReq industryReq);
    /**
     * 获取股票十天走势
     */
    List<TenDaysMarketResp> getTenDaysMarket(IndustryReq industryReq);
    /**
     * 获取两日差的行情
     */
    List<TwoDaysDifferResp> getTwoDaysDiffer(IndustryReq industryReq);
    /**
     * 获取公司信息
     */
    List<CompanyInfoResp> getCompanyInfo(IndustryReq industryReq);
    /**
     * 获取公司主营业务2
     */
    List<FinaMain2Resp> getFinaMain2(IndustryReq industryReq);
    /**
     * 获取公司主营业务3
     */
    List<FinaMain3Resp> getFinaMain3(IndustryReq industryReq);
    /**
     * 获取五日内涨停统计
     */
    List<FiveDaysLimitResp> getFiveDaysLimit(IndustryReq industryReq);
    /**
     * 获取资金流向
     */
    List<MoneyFlowResp> getMoneyFlow(IndustryReq industryReq);
    /**
     * 获取资金流向 百分比
     */
    List<MoneyFlowPctResp> getMoneyFlowPct(IndustryReq industryReq);
    /**
     * 获取周、月、年行情纵览
     */
    List<MarketOverviewResp> getMarketOverview(IndustryReq industryReq);
}

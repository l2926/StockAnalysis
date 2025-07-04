package com.example.springboot.analysis.service;

import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.*;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;

import java.util.List;

/**
 * @author lizijian
 * @desc 分析 服务层
 * @date 2024-09-25
 */
public interface AnalysisService {
    /**
     * 获取涨停股票
     */
    List<LimitResp> getLimit(LimitReq limitReq);
    /**
     * 获取涨停股票的主营业务信息
     */
    List<FinaMianResp> getFinaMain(LimitReq limitReq);
    /**
     * 获取涨停股票的持股信息
     */
    List<TopHoldResp> getTopHold(LimitReq limitReq);
    /**
     * 获取涨停股票，十日行情信息
     */
    List<TenDaysMarketResp> getTenDaysMarket(LimitReq limitReq);
    /**
     * 获取两日行情差
     */
    List<TwoDaysDifferResp> getTwoDaysDiffer(LimitReq limitReq);
    /**
     * 获取公司信息
     */
    List<CompanyInfoResp> getCompanyInfo(LimitReq limitReq);
    /**
     * 获取主营业务2
     */
    List<FinaMain2Resp> getFinaMain2Resp(LimitReq limitReq);
    /**
     * 获取主营业务3
     */
    List<FinaMain3Resp> getFinaMain3Resp(LimitReq limitReq);
    /**
     *  获取市值前100公司
     */
    List<Top100Resp> getTop100(LimitReq limitReq);
    /**
     * 获取五日内涨停统计
     */
    List<FiveDaysLimitResp> getFiveDaysLimit(LimitReq limitReq);
    /**
     * 获取资金流向
     */
    List<MoneyFlowResp> getMoneyFlow(LimitReq limitReq);
    /**
     * 获取资金流向 百分比
     */
    List<MoneyFlowPctResp> getMoneyFlowPct(LimitReq limitReq);
    /**
     * 获取涨停板股票
     */
    List<LimitBoardResp> getLimitBoard(LimitReq limitReq);
}

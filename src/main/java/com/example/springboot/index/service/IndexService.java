package com.example.springboot.index.service;


import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.req.DcMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;

import java.util.List;

/**
 * @author lizijian
 * @desc 指数统计 服务层
 * @date 2025-03-09
 */


public interface IndexService {
    /**
     * 获取大盘行情日线
     */
    DailyResp getDaily(IndexReq statisticsReq);
    /**
     * 获取大盘行情统计
     */
    List<StatisticResp> getStatistics(StatisticsReq statisticsReq);
    /**
     * 获取大盘行情统计 上边栏
     */
    StatisticsCountResp getStatisticsCount(StatisticsReq statisticsReq);
    /**
     * 获取大盘市值行情统计
     */
    List<StatisticResp> getStatisticsMv(StatisticsReq statisticsReq);
    /**
     * 获取大盘所有股票统计 控制器
     */
    List<StatisticAllResp> getStatisticsAll(StatisticsReq statisticsReq);
    /**
     * 获取开盘啦概念每日涨停统计
     */
    List<KplConceptResp> getKplConcept(StatisticsReq statisticsReq);
    /**
     * 获取开盘啦概念成员
     */
    List<KplConceptConsResp> getKplConceptCons(ConceptMemberReq conceptMemberReq);
    /**
     * 获取东方财富概念板块
     */
    List<DcIndexResp> getDcIndex(StatisticsReq statisticsReq);
    /**
     * 获取东方财富板块成分
     */
    List<DcMemberResp> getDcMember(ConceptMemberReq conceptMemberReq);
    /**
     * 获取主营业务2
     */
    List<FinaMain2Resp> getFinaMain2(ConceptMemberReq conceptMemberReq);
    /**
     * 获取主营业务3
     */
    List<FinaMain3Resp> getFinaMain3(ConceptMemberReq conceptMemberReq);
    /**
     * 获取公司信息
     */
    List<CompanyInfoResp> getCompanyInfo(ConceptMemberReq conceptMemberReq);
    /**
     * 获取五日涨停统计
     */
    List<FiveDaysLimitResp> getFiveDaysLimit(ConceptMemberReq conceptMemberReq);
    /**
     * 资金流向
     */
    List<MoneyFlowResp> getMoneyFlow(ConceptMemberReq req);
    /**
     * 资金流向 百分比
     */
    List<MoneyFlowPctResp> getMoneyFlowPct(ConceptMemberReq req);
    /**
     * 十日行情统计
     */
    List<TenDaysMarketResp> getTenDays(ConceptMemberReq req);
    /**
     * 获取行业基本面纵览——表格
     */
    List<StatisticsAllExcelResp> getStatisticsAllExcel(IndexReq indexReq);
    /**
     * 获取行业行情行情纵览——表格
     */
    List<StatisticsExcelResp> getStatisticsExcel(IndexReq indexReq);
    /**
     * 获取地域统计纵览——表格
     */
    List<StatisticsAreaExcelResp> getStatisticsAreaExcel(IndexReq indexReq);

    /**
     * 北向资金概览
     * @param indexReq
     * @return
     */
    HsgtDailyResp getHsgtOverview(IndexReq indexReq);
    /**
     * 东方财富-概念列表
     */
    List<DcIndex2Resp> getDcIndex2(IndexReq indexReq);
    /**
     * 东方财富-成员列表
     */
    List<DcMember2Resp> getDcMember2(DcMemberReq dcMemberReq);
    /**
     * 获取最强板块
     */
    List<LimitCptListResp> getLimitCptList(IndexReq indexReq);
    /**
     * 获取行情概览(L1,L2,L3)
     */
    List<StatisticsExcelResp> getStatisticsLevelExcel(IndexReq indexReq);
    /**
     * 获取基本面概览(L1,L2,L3)
     */
    List<StatisticsAllExcelResp> getStatisticsLevelAllExcel(IndexReq indexReq);
    /**
     * 获取日行情概览
     */
    List<DailyOverviewResp> getDailyPctOverview(IndexReq indexReq);
    /**
     * 获取周行情概览
     */
    List<WeekOverviewResp> getWeekOverview(IndexReq indexReq);
    /**
     * 获取月行情概览
     */
    List<MonthOverviewResp> getMonthOverview(IndexReq indexReq);
    /**
     * 获取季行情概览
     */
    List<SeasonOverviewResp> getSeasonOverview(IndexReq indexReq);
    /**
     * 获取年行情概览
     */
    List<YearOverviewResp> getYearOverview(IndexReq indexReq);
}

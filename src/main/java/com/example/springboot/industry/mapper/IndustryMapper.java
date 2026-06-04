package com.example.springboot.industry.mapper;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TwoDaysDifferResp;
import com.example.springboot.analysis.vo.vo.HfqDailyVo;
import com.example.springboot.industry.vo.req.IndustryReq;
import com.example.springboot.industry.vo.resp.*;
import org.apache.ibatis.annotations.Mapper;

import java.time.Month;
import java.util.List;

/**
 * @author: lizijian
 * @desc 行业数据 mapper
 * @date 2024-09-27
 */

@Mapper
public interface IndustryMapper {
    List<FundmentalResp> getFundmental(IndustryReq industryReq);
    List<FinaMainResp> getFinaMain(IndustryReq industryReq);
    List<TopHoldResp> getTopHold(IndustryReq industryReq);
    List<TenDaysMarketResp> getTenDaysMarket(IndustryReq industryReq);
    List<CompanyInfoResp> getCompanyInfo(IndustryReq industryReq);
    List<FinaMain2Resp> getFinaMain2(IndustryReq industryReq);
    List<FinaMain3Resp> getFinaMain3(IndustryReq industryReq);
    List<FiveDaysLimitResp> selectFiveDaysLimit(IndustryReq industryReq);
    List<MoneyFlowResp> selectMoneyFlow(IndustryReq industryReq);

    //任意两条行情差
    List<HfqDailyVo> selectCommon1(IndustryReq industryReq);
    List<HfqDailyVo> selectCommon2(IndustryReq industryReq);
    List<TwoDaysDifferResp> selectIndustryMember(IndustryReq industryReq);

    /**
     * 历史行情
     * @param industryReq
     * @return
     */
    List<WeekOverviewResp> selectWeekOverview(IndustryReq industryReq);
    List<MonthOverviewResp> selectMonthOverview(IndustryReq industryReq);
    List<SeasonOverviewResp> selectSeasonOverview(IndustryReq industryReq);
    List<YearOverviewResp> selectYearOverview(IndustryReq industryReq);
    List<DailyOverviewResp> selectDailyOverview(IndustryReq industryReq);
    List<DailyOverviewResp> selectDailyOriginOverview(IndustryReq industryReq);
    List<DailyOverviewResp> selectDailyPbOverview(IndustryReq industryReq);
    List<DailyOverviewResp> selectDailyGrowthOverview(IndustryReq industryReq);
    List<WeekOverviewResp> selectWeekOriginOverview(IndustryReq industryReq);
    List<WeekOverviewResp> selectWeekPbOverview(IndustryReq industryReq);
    List<WeekOverviewResp> selectWeekGrowthOverview(IndustryReq industryReq);
    List<MonthOverviewResp> selectMonthOriginOverview(IndustryReq industryReq);
    List<MonthOverviewResp> selectMonthPbOverview(IndustryReq industryReq);
    List<MonthOverviewResp> selectMonthGrowthOverview(IndustryReq industryReq);
    List<SeasonOverviewResp> selectSeasonOriginOverview(IndustryReq industryReq);
    List<SeasonOverviewResp> selectSeasonPbOverview(IndustryReq industryReq);
    List<SeasonOverviewResp> selectSeasonGrowthOverview(IndustryReq industryReq);
    List<YearOverviewResp> selectYearOriginOverview(IndustryReq industryReq);
    List<YearOverviewResp> selectYearPbOverview(IndustryReq industryReq);
    List<YearOverviewResp> selectYearGrowthOverview(IndustryReq industryReq);
}

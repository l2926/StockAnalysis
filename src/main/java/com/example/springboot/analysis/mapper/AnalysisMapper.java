package com.example.springboot.analysis.mapper;

import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.*;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.analysis.vo.resp.TopHoldResp;
import com.example.springboot.analysis.vo.vo.HfqDailyVo;
import com.example.springboot.industry.vo.req.IndustryReq;
import com.example.springboot.industry.vo.resp.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lizijian
 * @desc 分析mapper
 * @date 2024-09-26
 */

@Mapper
public interface AnalysisMapper {
    Integer selectCount();

    List<LimitResp> getLimit(LimitReq limitReq);

    List<FinaMianResp> getFinaMain(LimitReq limitReq);

    List<TopHoldResp> getTopHold(LimitReq limitReq);

    List<TenDaysMarketResp> getTenDaysMarket(LimitReq limitReq);
    List<TenDaysMarketResp> getTenDaysMarketOrigin(LimitReq limitReq);

    List<CompanyInfoResp> getCompanyInfo(LimitReq limitReq);

    List<FinaMain2Resp> getFinaMain2(LimitReq limitReq);

    List<FinaMain3Resp> getFinaMain3(LimitReq limitReq);
    List<Top100Resp> getTop100(LimitReq limitReq);

    //五日涨跌停
    List<FiveDaysLimitResp> selectFiveDaysLimit(LimitReq limitReq);
    List<MoneyFlowResp> selectMoneyFlow(LimitReq limitReq);

    //两日行情差
    List<HfqDailyVo> selectCommon1(LimitReq limitReq);
    List<HfqDailyVo> selectCommon2(LimitReq limitReq);
    List<TwoDaysDifferResp> selectIndustryMember(LimitReq limitReq);
    List<LimitBoardResp> selectLimitBoard(LimitReq limitReq);

    // 历史行情纵览
    List<WeekOverviewResp> selectWeekOverview(LimitReq limitReq);
    List<MonthOverviewResp> selectMonthOverview(LimitReq limitReq);
    List<SeasonOverviewResp> selectSeasonOverview(LimitReq limitReq);
    List<YearOverviewResp> selectYearOverview(LimitReq limitReq);
    List<DailyOverviewResp> selectDailyOverview(LimitReq limitReq);
    List<DailyOverviewResp> selectDailyOriginOverview(LimitReq limitReq);
    List<DailyOverviewResp> selectDailyPbOverview(LimitReq limitReq);
    List<DailyOverviewResp> selectDailyGrowthOverview(LimitReq limitReq);
    List<HotMoneyResp> selectHotMoneyDetail(LimitReq limitReq);
}

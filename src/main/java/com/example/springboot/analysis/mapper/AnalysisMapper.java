package com.example.springboot.analysis.mapper;

import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.*;
import com.example.springboot.analysis.vo.vo.HfqDailyVo;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
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
}

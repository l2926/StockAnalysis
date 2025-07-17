package com.example.springboot.index.mapper;

import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.index.vo.vo.DailyVo;
import com.example.springboot.index.vo.vo.StatisticCommon;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lizijian
 * @desc 查询指数行情mapper
 * @date 2025-03-09
 */

@Mapper
public interface IndexMapper {
    Integer selectCount(Integer selectId);

    //行业统计
    List<DailyVo> selectDaily(IndexReq statisticsReq);

    //获取统计数据
    List<StatisticCommon> selectAllCommon(StatisticsReq statisticsReq);
    List<StatisticResp> selectSwAllIndustry(StatisticsReq statisticsReq);
    Integer selectStatisticsCountUp(StatisticsReq statisticsReq);   //统计行情 上面的上涨统计
    Integer selectStatisticsCountDown(StatisticsReq statisticsReq); //统计行情 上面的下跌统计

    //获取统计所有数据
    List<StatisticAllResp> selectSwAllIndustry2(StatisticsReq statisticsReq);

    //开盘啦概念统计
    List<KplConceptResp> selectKplConcept(StatisticsReq statisticsReq);
    List<KplConceptConsResp> selectKplConceptCons(ConceptMemberReq conceptMemberReq);

    //东方财富 概念统计
    List<DcIndexResp> selectDcIndex(StatisticsReq statisticsReq);
    List<DcMemberResp> selectDcMember(ConceptMemberReq conceptMemberReq);

    //主营业务和公司信息
    List<FinaMain2Resp> selectFinaMain2(ConceptMemberReq conceptMemberReq);
    List<FinaMain3Resp> selectFinaMain3(ConceptMemberReq conceptMemberReq);
    List<CompanyInfoResp> selectCompanyInfo(ConceptMemberReq conceptMemberReq);

    //五日行情
    List<FiveDaysLimitResp> selectFiveDaysLimit(ConceptMemberReq conceptMemberReq);
    List<TenDaysMarketResp> selectTenDaysMarket(ConceptMemberReq conceptMemberReq);

    //资金流向
    List<MoneyFlowResp> selectMoneyFlow(ConceptMemberReq conceptMemberReq);
}

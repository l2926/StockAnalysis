package com.example.springboot.index.mapper;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.req.DcMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.index.vo.vo.*;
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
    Double selectShPct(StatisticsReq statisticsReq);   //上证指数涨跌幅
    Double selectSzPct(StatisticsReq statisticsReq);   //深证指数涨跌幅
    Double selectSmallPct(StatisticsReq statisticsReq);    //中小板指数涨跌幅
    Double selectStartUpPct(StatisticsReq statisticsReq);  //创业板指数涨跌幅

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
    //获取行业基本面纵览统计——表格
    List<StatisticsAllExcelResp> selectStatisticsAllExcel(IndexReq indexReq);
    //设置查询mod
    void setSqlMode();
    //获取行业行情纵览——表格
    List<StatisticsExcelResp> selectStatisticsExcel(IndexReq indexReq);
    //统计该行业所有的数据
    List<StatisticsExcelVo> selectStatisticsExcel2(IndexReq indexReq);
    List<ShenWanDailyVo> selectShenWanDaily(IndexReq indexReq);

    //统计该地域所有的数据
    List<StatisticsAreaExcelResp> selectStatisticsAreaExcel(IndexReq indexReq);
    List<HsgtDailyVo> selectHsgtDaily(IndexReq indexReq);
    //东方财富-概念列表
    List<DcIndex2Resp> selectDcIndex2(IndexReq indexReq);
    //东方财富-成员列表
    List<DcMember2Resp> selectDcMember2(DcMemberReq dcMemberReq);
    List<LimitCptListResp> selectLimitCptList(IndexReq indexReq);
    /**
     * 行情板块概览(L1,L2,L3)
     */
    List<StatisticsExcelResp> selectLevelStatisticsExcel(IndexReq indexReq);
    List<StatisticsExcelVo> selectLevelStatisticsExcel2(IndexReq indexReq);
    List<ShenWanDailyVo> selectLevelShenWanDaily(IndexReq indexReq);
    // 基本面概览(L1,L2,L3)
    List<StatisticsAllExcelResp> selectStatisticsLevelAllExcel(IndexReq indexReq);
}

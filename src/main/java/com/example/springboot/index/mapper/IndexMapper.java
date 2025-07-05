package com.example.springboot.index.mapper;

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
    List<DailyVo> selectDaily(IndexReq statisticsReq);
    List<StatisticCommon> selectAllCommon(StatisticsReq statisticsReq);
    List<StatisticResp> selectSwAllIndustry(StatisticsReq statisticsReq);
    List<StatisticAllResp> selectSwAllIndustry2(StatisticsReq statisticsReq);
    List<KplConceptResp> selectKplConcept(StatisticsReq statisticsReq);
    List<KplConceptConsResp> selectKplConceptCons(ConceptMemberReq conceptMemberReq);
    List<DcIndexResp> selectDcIndex(StatisticsReq statisticsReq);
    List<DcMemberResp> selectDcMember(ConceptMemberReq conceptMemberReq);
    List<FinaMain2Resp> selectFinaMain2(ConceptMemberReq conceptMemberReq);
    List<FinaMain3Resp> selectFinaMain3(ConceptMemberReq conceptMemberReq);
    List<CompanyInfoResp> selectCompanyInfo(ConceptMemberReq conceptMemberReq);
}

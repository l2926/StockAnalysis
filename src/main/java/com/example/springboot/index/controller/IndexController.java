package com.example.springboot.index.controller;

import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.service.IndexService;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizijian
 * @desc 指数统计 控制器类
 * @date 2025-03-09
 */

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;

    @PostMapping("/daily")
    public DailyResp getDaily(@RequestBody IndexReq indexReq){
        return indexService.getDaily(indexReq);
    }

    @PostMapping("/statistics")
    public List<StatisticResp> getStatistics(@RequestBody StatisticsReq statisticsReq){
        return indexService.getStatistics(statisticsReq);
    }

    @PostMapping("/statistics_all")
    public List<StatisticAllResp> getStatisticsAll(@RequestBody StatisticsReq statisticsReq){
        System.out.println("----statistics_all controller----");
        return indexService.getStatisticsAll(statisticsReq);
    }

    @PostMapping("kpl_concept")
    public List<KplConceptResp> getKplConcept(@RequestBody StatisticsReq statisticsReq){
        System.out.println("----kpl_concept controller----");
        return indexService.getKplConcept(statisticsReq);
    }

    @PostMapping("kpl_concept_cons")
    public List<KplConceptConsResp> getKplConceptCons(@RequestBody ConceptMemberReq conceptMemberReq){
        System.out.println("----kpl_concept_cons controller----");
        return indexService.getKplConceptCons(conceptMemberReq);
    }

    @PostMapping("dc_index")
    public List<DcIndexResp> getDcIndex(@RequestBody StatisticsReq statisticsReq){
        System.out.println("----dc_index controller----");
        return indexService.getDcIndex(statisticsReq);
    }

    @PostMapping("dc_member")
    public List<DcMemberResp> getDcMember(@RequestBody ConceptMemberReq conceptMemberReq){
        System.out.println("----dc_member controller----");
        return indexService.getDcMember(conceptMemberReq);
    }

    @PostMapping("fina_main2")
    public List<FinaMain2Resp> getFinaMain2(@RequestBody ConceptMemberReq conceptMemberReq){
        System.out.println("----fina_main2 controller----");
        return indexService.getFinaMain2(conceptMemberReq);
    }

    @PostMapping("fina_main3")
    public List<FinaMain3Resp> getFinaMain3(@RequestBody ConceptMemberReq conceptMemberReq){
        System.out.println("----fina_main3 controller----");
        return indexService.getFinaMain3(conceptMemberReq);
    }

    @PostMapping("company_info")
    public List<CompanyInfoResp> getCompanyInfo(@RequestBody ConceptMemberReq conceptMemberReq){
        System.out.println("----company_info controller----");
        return indexService.getCompanyInfo(conceptMemberReq);
    }
}

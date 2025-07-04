package com.example.springboot.analysis.controller;

import com.example.springboot.analysis.service.AnalysisService;
import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.*;
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
 * @desc 分析 控制器类
 * @date 2024-09-25
 */

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    AnalysisService analysisService;

    @PostMapping("/limit")
    public List<LimitResp> getLimit(@RequestBody LimitReq limitReq){
        return  analysisService.getLimit(limitReq);
    }

    @PostMapping("/fina_main")
    public List<FinaMianResp> getFinaMain(@RequestBody LimitReq limitReq){
        return analysisService.getFinaMain(limitReq);
    }

    @PostMapping("/top_hold")
    public List<TopHoldResp> getTopHold(@RequestBody LimitReq limitReq){
        return analysisService.getTopHold(limitReq);
    }

    @PostMapping("/ten_days_market")
    public List<TenDaysMarketResp> getTenDaysMarket(@RequestBody LimitReq limitReq){
        return analysisService.getTenDaysMarket(limitReq);
    }

    @PostMapping("company_info")
    public List<CompanyInfoResp> getCompanyInfo(@RequestBody LimitReq limitReq){
        System.out.println("----analysis company_info Controller----");
        return analysisService.getCompanyInfo(limitReq);
    }

    @PostMapping("fina_main2")
    public List<FinaMain2Resp> getFinaMain2Resp(@RequestBody LimitReq limitReq){
        System.out.println("----analysis fina_main2 Controller----");
        return analysisService.getFinaMain2Resp(limitReq);
    }

    @PostMapping("fina_main3")
    public List<FinaMain3Resp> getFinaMain3Resp(@RequestBody LimitReq limitReq){
        System.out.println("----analysis fina_main3 Controller----");
        return analysisService.getFinaMain3Resp(limitReq);
    }

    @PostMapping("top100")
    public List<Top100Resp> getTop100(@RequestBody LimitReq limitReq){
        System.out.println("----top100 Controller----");
        return analysisService.getTop100(limitReq);
    }

    @PostMapping("two_days_differ")
    public List<TwoDaysDifferResp> getTwoDaysDiffer(@RequestBody LimitReq limitReq){
        System.out.println("----two_days_differ analysis Controller----");
        return analysisService.getTwoDaysDiffer(limitReq);
    }


    @PostMapping("five_days_limit")
    public List<FiveDaysLimitResp> getFiveDaysLimit(@RequestBody LimitReq limitReq){
        System.out.println("----five_days_limit analysis Controller----");
        return analysisService.getFiveDaysLimit(limitReq);
    }

    @PostMapping("money_flow")
    public List<MoneyFlowResp> getMoneyFlow(@RequestBody LimitReq limitReq){
        System.out.println("----money_flow analysis Controller----");
        return analysisService.getMoneyFlow(limitReq);
    }

    @PostMapping("money_flow_pct")
    public List<MoneyFlowPctResp> getMoneyFlowPct(@RequestBody LimitReq limitReq){
        System.out.println("----money_flow_pct analysis Controller----");
        return analysisService.getMoneyFlowPct(limitReq);
    }

    @PostMapping("limit_board")
    public List<LimitBoardResp> getLimitBoard(@RequestBody LimitReq limitReq){
        System.out.println("----limit_board controller----");
        return analysisService.getLimitBoard(limitReq);
    }
}

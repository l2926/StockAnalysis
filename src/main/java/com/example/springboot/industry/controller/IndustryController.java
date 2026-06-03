package com.example.springboot.industry.controller;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TwoDaysDifferResp;
import com.example.springboot.industry.service.IndustryService;
import com.example.springboot.industry.vo.req.IndustryReq;
import com.example.springboot.industry.vo.resp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

/**
 * @author lizijian
 * @desc 行业控制器
 * @date 2024-09-25
 */

@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Autowired
    IndustryService industryService;

    @PostMapping("/fundmental")
    public List<FundmentalResp> getFundmental(@RequestBody IndustryReq industryReq){
        System.out.println("----industry fundmental conroller====");
        return industryService.getFundmental(industryReq);
    }

    @PostMapping("market")
    public List<FundmentalResp> getMarket(@RequestBody IndustryReq industryReq){
        System.out.println("----industry market controller----");
        return industryService.getMarket(industryReq);
    }

    @PostMapping("/fina_main")
    public List<FinaMainResp> getFinaMain(@RequestBody IndustryReq industryReq){
        return industryService.getFinaMain(industryReq);
    }

    @PostMapping("/top_hold")
    public List<TopHoldResp> getTopHold(@RequestBody IndustryReq industryReq){
        return industryService.getTopHold(industryReq);
    }

    @PostMapping("/ten_days_market")
    public List<TenDaysMarketResp> getTenDaysMarket(@RequestBody IndustryReq industryReq){
        return industryService.getTenDaysMarket(industryReq);
    }

    @PostMapping("/company_info")
    public List<CompanyInfoResp> getCompanyInfo(@RequestBody IndustryReq industryReq){
        System.out.println("----company_info Controller----");
        return industryService.getCompanyInfo(industryReq);
    }

    @PostMapping("/fina_main2")
    public List<FinaMain2Resp> getFinaMain2(@RequestBody IndustryReq industryReq){
        System.out.println("----getFinaMain2 Controller----");
        return industryService.getFinaMain2(industryReq);
    }

    @PostMapping("/fina_main3")
    public List<FinaMain3Resp> getFinaMain3(@RequestBody IndustryReq industryReq){
        System.out.println("----getFinaMain3 Controller----");
        return industryService.getFinaMain3(industryReq);
    }

    @PostMapping("/two_days_differ")
    public List<TwoDaysDifferResp> getTwoDaysDiffer(@RequestBody IndustryReq industryReq){
        System.out.println("----two_days_differ industry Controller----");
        return industryService.getTwoDaysDiffer(industryReq);
    }


    @PostMapping("five_days_limit")
    public List<FiveDaysLimitResp> getFiveDaysLimit(@RequestBody IndustryReq industryReq){
        System.out.println("----five_days_limit industry Controller----");
        return industryService.getFiveDaysLimit(industryReq);
    }

    @PostMapping("money_flow")
    public List<MoneyFlowResp> getMoneyFlow(@RequestBody IndustryReq industryReq){
        System.out.println("----money_flow industry Controller----");
        return industryService.getMoneyFlow(industryReq);
    }

    @PostMapping("money_flow_pct")
    public List<MoneyFlowPctResp> getMoneyFlowPct(@RequestBody IndustryReq industryReq){
        System.out.println("----money_flow_pct industry Controller----");
        return industryService.getMoneyFlowPct(industryReq);
    }

    @PostMapping("week_overview")
    public List<WeekOverviewResp> getWeekOverview(@RequestBody IndustryReq industryReq){
        System.out.println("----week_overview inidustry Controller----");
        return industryService.getWeekOverview(industryReq);
    }
    @PostMapping("month_overview")
    public List<MonthOverviewResp> getMonthOverview(@RequestBody IndustryReq industryReq){
        System.out.println("----month_overview inidustry Controller----");
        return industryService.getMonthOverview(industryReq);
    }
    @PostMapping("season_overview")
    public List<SeasonOverviewResp> getSeasonOverview(@RequestBody IndustryReq industryReq){
        System.out.println("----season_overview inidustry Controller----");
        return industryService.getSeasonOverview(industryReq);
    }
    @PostMapping("year_overview")
    public List<YearOverviewResp> getYearOverview(@RequestBody IndustryReq industryReq){
        System.out.println("----year_overview inidustry Controller----");
        return industryService.getYearOverview(industryReq);
    }

    @PostMapping("daily_overview")
    public List<DailyOverviewResp> getDailyOverview(@RequestBody IndustryReq industryReq){
        System.out.println("----daily_overview Controller----");
        return industryService.getDailyOverview(industryReq);
    }
}

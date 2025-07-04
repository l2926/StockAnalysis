package com.example.springboot.market.controller;

import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import com.example.springboot.market.service.MarketService;
import com.example.springboot.market.vo.req.MarketReq;
import com.example.springboot.market.vo.resp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizijian
 * @desc 市场控制器
 * @date 2024-06-23
 */

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    @PostMapping("/daily")
    public DailyResp getDaily(@RequestBody MarketReq marketReq){
        System.out.println("----getDaily----");
        return marketService.getDaily(marketReq);
    }

    @PostMapping("/finance_center")
    public FinanceCenterResp getFinanceCenter(@RequestBody MarketReq marketReq){
        System.out.println("----getFinanceCenter controller----");
        return marketService.getFianceCenter(marketReq);
    }

    @PostMapping("/asset_center")
    public AssetCenterResp getAssetCenter(@RequestBody MarketReq marketReq){
        System.out.println("----asset_center Controller----");
        return marketService.getAssetCenter(marketReq);
    }

    @PostMapping("/subordinate")
    public List<SubordinateResp> getSubordinate(@RequestBody MarketReq marketReq){
        System.out.println("----getSubordinate----");
        return marketService.getSubordinate(marketReq);
    }

    @PostMapping("/log_daily")
    public LogDailyResp getLogDaily(@RequestBody MarketReq marketReq){
        System.out.println("----log_daily controller----");
        return marketService.getLogDaily(marketReq);
    }

    @PostMapping("/money_flow")
    public MoneyFlowResp getMoneyFlow(@RequestBody MarketReq marketReq){
        System.out.println("----money_flow market controller----");
        return marketService.getMoneyFlow(marketReq);
    }

    @PostMapping("fina_mian2")
    public FinaMain2Resp getFinaMain2(@RequestBody MarketReq marketReq){
        System.out.println("-----fina_main2 market controller----");
        return marketService.getFinaMain2(marketReq);
    }

    @PostMapping("fina_mian3")
    public FinaMain3Resp getFinaMain3(@RequestBody MarketReq marketReq){
        System.out.println("-----fina_main3 market controller----");
        return marketService.getFinaMain3(marketReq);
    }

    @PostMapping("company_info")
    public CompanyInfoResp getCompanyInfo(@RequestBody MarketReq marketReq){
        System.out.println("-----company_info market controller----");
        return marketService.getCompanyInfo(marketReq);
    }
}

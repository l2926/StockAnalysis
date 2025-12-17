package com.example.springboot.finance.controller;

import com.example.springboot.finance.service.FinanceService;
import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizijian
 * @desc 财务 控制器
 * @date 2025-06-08
 */

@RestController
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    FinanceService financeService;

    @PostMapping("/finance_overview")
    public List<FinanceOverviewResp> getFinanceOverView(@RequestBody FinanceReq financeReq){
        System.out.println("----finance_overview Controller");
        System.out.println(financeReq.getIndustryCode());
        return financeService.getFinanceOverview(financeReq);
    }
}

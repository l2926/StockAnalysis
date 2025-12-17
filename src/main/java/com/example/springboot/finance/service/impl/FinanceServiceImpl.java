package com.example.springboot.finance.service.impl;

import com.example.springboot.finance.mapper.FinanceMapper;
import com.example.springboot.finance.service.FinanceService;
import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    FinanceMapper financeMapper;

    @Override
    public List<FinanceOverviewResp> getFinanceOverview(FinanceReq financeReq){
        System.out.println("----finance_overview Service----");
        List<FinanceOverviewResp> financeOverviewRespList = financeMapper.getFinanceOverview(financeReq);
        AtomicInteger index = new AtomicInteger(1);
        financeOverviewRespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            if(resp.getTotalAssets() != null){
                resp.setTotalAssets(Double.parseDouble(String.format("%.2f",resp.getTotalAssets()/100000000)));
            }
            if(resp.getTotalLiab() != null){
                resp.setTotalLiab(Double.parseDouble(String.format("%.2f",resp.getTotalLiab()/100000000)));
            }
            if(resp.getTotalAssets() != null && resp.getTotalLiab() != null){
                resp.setNetAssets(Double.parseDouble(String.format("%.2f",resp.getTotalAssets() - resp.getTotalLiab())));
            }
            if(resp.getTotalAssets() != null && resp.getNetAssets() != null){
                resp.setLevelRate(Double.parseDouble(String.format("%.2f",resp.getTotalLiab() / resp.getNetAssets())));
            }
            if(resp.getTotalRevenue() != null){
                resp.setTotalRevenue(Double.parseDouble(String.format("%.2f",resp.getTotalRevenue()/100000000)));
            }
            if(resp.getRevenue() != null){
                resp.setRevenue(Double.parseDouble(String.format("%.2f",resp.getRevenue()/100000000)));
            }
            if(resp.getIntIncome() != null){
                resp.setIntIncome(Double.parseDouble(String.format("%.2f",resp.getIntIncome()/100000000)));
            }
            if(resp.getTotalCogs() != null){
                resp.setTotalCogs(Double.parseDouble(String.format("%.2f",resp.getTotalCogs()/100000000)));
            }
            if(resp.getOperCost() != null){
                resp.setOperCost(Double.parseDouble(String.format("%.2f",resp.getOperCost()/100000000)));
            }
            if(resp.getIntExp() != null){
                resp.setIntExp(Double.parseDouble(String.format("%.2f",resp.getIntExp()/100000000)));
            }
            if(resp.getSellExp() != null){
                resp.setSellExp(Double.parseDouble(String.format("%.2f",resp.getSellExp()/100000000)));
            }
            if(resp.getAdminExp() != null){
                resp.setAdminExp(Double.parseDouble(String.format("%.2f",resp.getAdminExp()/100000000)));
            }
            if(resp.getFinExp() != null){
                resp.setFinExp(Double.parseDouble(String.format("%.2f",resp.getFinExp()/100000000)));
            }
            if(resp.getOperateProfit() != null){
                resp.setOperateProfit(Double.parseDouble(String.format("%.2f",resp.getOperateProfit()/100000000)));
            }
            if(resp.getTotalProfit() != null){
                resp.setTotalProfit(Double.parseDouble(String.format("%.2f",resp.getTotalProfit()/100000000)));
            }
            if(resp.getNIncome() != null){
                resp.setNIncome(Double.parseDouble(String.format("%.2f",resp.getNIncome()/100000000)));
            }
            if(resp.getNIncomeAttrP() != null){
                resp.setNIncomeAttrP(Double.parseDouble(String.format("%.2f",resp.getNIncomeAttrP()/100000000)));
            }
            if(resp.getNIncomeAttrP() != null && resp.getTotalRevenue() != null){
                resp.setProfitRate(Double.parseDouble(String.format("%.2f",resp.getNIncomeAttrP()/resp.getTotalRevenue())));
            }
            if(resp.getNIncomeAttrP() != null && resp.getNetAssets() != null){
                resp.setRoe(Double.parseDouble(String.format("%.2f",resp.getNIncomeAttrP()/resp.getNetAssets())));
            }
            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv()/10000)));
            }
            if(resp.getTotalMv() != null && resp.getPb() != null){
                resp.setAssets(Double.parseDouble(String.format("%.2f",resp.getTotalMv()/resp.getPb())));
            }
            resp.setPb(0.0);
        });
        return financeOverviewRespList;
    }}

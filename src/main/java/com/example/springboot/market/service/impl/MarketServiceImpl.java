package com.example.springboot.market.service.impl;

import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import com.example.springboot.market.mapper.MarketMapper;
import com.example.springboot.market.service.MarketService;
import com.example.springboot.market.vo.req.MarketReq;
import com.example.springboot.market.vo.resp.*;
import com.example.springboot.market.vo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    MarketMapper marketMapper;

    @Override
    public DailyResp getDaily(MarketReq marketReq){
        String tsCode = marketReq.getTsCode();
        marketReq.setSymbol(tsCode.split("\\.")[0]);

        //日期与字符串的互相转换
        String dateStr = marketReq.getTradeDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tradeDate = LocalDate.parse(dateStr,formatter);
        LocalDate startDate = tradeDate.minusYears(50);

        //判断查看日期范围
        if(marketReq.getParaId() == 1){
            startDate = tradeDate.minusYears(1);
            tradeDate = tradeDate.plusMonths(3);
        }
        if(marketReq.getParaId() == 3){
            startDate = tradeDate.minusYears(3);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 5){
            startDate = tradeDate.minusYears(5);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 10){
            startDate = tradeDate.minusYears(10);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 20){
            startDate = tradeDate.minusYears(20);
            tradeDate = tradeDate.plusYears(1);
        }

        marketReq.setTradeDate(tradeDate.format(formatter));
        marketReq.setStartDate(startDate.format(formatter));

        List<DailyVo> dailyVoList = marketMapper.selectDaily(marketReq);

        Double openFirstDay = dailyVoList.get(0).getOpen();

        dailyVoList.stream().forEach(dailyVo -> {
            dailyVo.setOpen(Double.parseDouble(String.format("%.2f",10*dailyVo.getOpen()/openFirstDay)));
            dailyVo.setClose(Double.parseDouble(String.format("%.2f",10*dailyVo.getClose()/openFirstDay)));
            dailyVo.setHigh(Double.parseDouble(String.format("%.2f",10*dailyVo.getHigh()/openFirstDay)));
            dailyVo.setLow(Double.parseDouble(String.format("%.2f",10*dailyVo.getLow()/openFirstDay)));
            dailyVo.setAmount(Double.parseDouble(String.format("%.2f",dailyVo.getAmount() / 100000)));
            dailyVo.setTotalMv(Double.parseDouble(String.format("%.2f",dailyVo.getTotalMv() / 10000)));

            if(dailyVo.getPe() != null){
                dailyVo.setPe(Double.parseDouble(String.format("%.2f",dailyVo.getPe())));
            }else{
                dailyVo.setPe(0.0);
            }
            if(dailyVo.getPb() != null){
                dailyVo.setPb(Double.parseDouble(String.format("%.2f",dailyVo.getPb())));
            }else {
                dailyVo.setPb(0.0);
            }
            dailyVo.setPctChg(Double.parseDouble(String.format("%.2f",dailyVo.getPctChg())));
            dailyVo.setTurnOverRate(Double.parseDouble(String.format("%.2f",dailyVo.getTurnOverRate())));
            dailyVo.setTurnOverRateF(Double.parseDouble(String.format("%.2f",dailyVo.getTurnOverRateF())));

        });

        DailyResp dailyResp = new DailyResp();
        dailyResp.setTsCode(tsCode);
        dailyResp.setTsName(marketReq.getName());
        dailyResp.setDailyVoList(dailyVoList);
        return dailyResp;
    }

    @Override
    public List<SubordinateResp> getSubordinate(MarketReq marketReq){
        List<SubordinateResp> subordinateRespList = marketMapper.selectSubordinate(marketReq);
        subordinateRespList.get(0).setIdx(1);
        return subordinateRespList;
    }

    @Override
    public FinanceCenterResp getFianceCenter(MarketReq marketReq){
        System.out.println("----finance_center service----");

        String tsCode = marketReq.getTsCode();
        marketReq.setSymbol(tsCode.split("\\.")[0]);

        List<FinanceCenterVo> financeCenterVoList = marketMapper.selectFinanceCenter(marketReq);

        financeCenterVoList.stream().forEach(financeCenterVo -> {
            if(financeCenterVo.getPs() != null){
                financeCenterVo.setPs(Double.parseDouble(String.format("%.2f",financeCenterVo.getPs())));
            }
            if(financeCenterVo.getPe() != null){
                financeCenterVo.setPe(Double.parseDouble(String.format("%.2f",financeCenterVo.getPe())));
            }
            if(financeCenterVo.getPb() != null){
                financeCenterVo.setPb(Double.parseDouble(String.format("%.2f",financeCenterVo.getPb())));
            }
            if(financeCenterVo.getTotalMv() != null){
                financeCenterVo.setTotalMv(Double.parseDouble(String.format("%.2f",financeCenterVo.getTotalMv() / 10000)));
            }

            if(financeCenterVo.getPe() != null && financeCenterVo.getTotalMv() != null){
                financeCenterVo.setProfit(Double.parseDouble(String.format("%.2f",financeCenterVo.getTotalMv() / financeCenterVo.getPe())));
            }
            if(financeCenterVo.getPs()!=null && financeCenterVo.getTotalMv()!=null){
                financeCenterVo.setRevenue(Double.parseDouble(String.format("%.2f",financeCenterVo.getTotalMv() / financeCenterVo.getPs())));
            }
            if(financeCenterVo.getPb()!=null && financeCenterVo.getTotalMv()!=null){
                financeCenterVo.setAsset(Double.parseDouble(String.format("%.2f",financeCenterVo.getTotalMv() / financeCenterVo.getPb())));
            }
            if(financeCenterVo.getRevenue()!=null && financeCenterVo.getProfit()!=null){
                financeCenterVo.setProfitRate(Double.parseDouble(String.format("%.2f",financeCenterVo.getProfit() / financeCenterVo.getRevenue())));
            }
            if(financeCenterVo.getAsset()!=null && financeCenterVo.getProfit()!=null){
                financeCenterVo.setRoe(Double.parseDouble(String.format("%.2f",financeCenterVo.getProfit() / financeCenterVo.getAsset())));
            }
        });

        FinanceCenterResp financeCenterResp = new FinanceCenterResp();
        financeCenterResp.setTsCode(marketReq.getTsCode());
        financeCenterResp.setTsName(marketReq.getName());
        financeCenterResp.setFinanceCenterVoList(financeCenterVoList);
        return financeCenterResp;
    }

    @Override
    public AssetCenterResp getAssetCenter(MarketReq marketReq){
        System.out.println("----asset_center Service");
        String tsCode = marketReq.getTsCode();
        marketReq.setSymbol(tsCode.split("\\.")[0]);

        List<AssetCenterVo>  assetCenterVoList = marketMapper.selectAssetCenter(marketReq);
        assetCenterVoList.stream().forEach(assetCenterVo -> {
            assetCenterVo.setTotalAssets(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalAssets()/100000000)));
            assetCenterVo.setTotalLiab(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalLiab()/100000000)));
            assetCenterVo.setNetAssets(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalAssets() - assetCenterVo.getTotalLiab())));
            assetCenterVo.setTotalRevenue(Double.parseDouble(String.format("%.2f", assetCenterVo.getTotalRevenue()/100000000)));
            assetCenterVo.setTotalCogs(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalCogs()/100000000)));
//            assetCenterVo.setNincomeAttrP(Double.parseDouble(String.format("%.2f",assetCenterVo.getNincomeAttrP()/100000000)));
            assetCenterVo.setNincomeAttrP(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalRevenue() - assetCenterVo.getTotalCogs())));
            assetCenterVo.setLevelRate(Double.parseDouble(String.format("%.2f",assetCenterVo.getTotalAssets() / assetCenterVo.getNetAssets())));
            assetCenterVo.setProfitRate(Double.parseDouble(String.format("%.2f",assetCenterVo.getNincomeAttrP()/assetCenterVo.getTotalRevenue())));
            assetCenterVo.setRoe(Double.parseDouble(String.format("%.2f",assetCenterVo.getNincomeAttrP()/assetCenterVo.getNetAssets())));
        });
        AssetCenterResp assetCenterResp = new AssetCenterResp();
        assetCenterResp.setTsCode(marketReq.getTsCode());
        assetCenterResp.setTsName(marketReq.getName());
        assetCenterResp.setAssetCenterVoList(assetCenterVoList);
        return assetCenterResp;
    }

    @Override
    public LogDailyResp getLogDaily(MarketReq marketReq){
        System.out.println("----log_daily service----");
        String tsCode = marketReq.getTsCode();
        marketReq.setSymbol(tsCode.split("\\.")[0]);

        //日期与字符串的互相转换
        String dateStr = marketReq.getTradeDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tradeDate = LocalDate.parse(dateStr,formatter);
        LocalDate startDate = tradeDate.minusYears(50);

        //判断查看日期范围
        if(marketReq.getParaId() == 1){
            startDate = tradeDate.minusYears(1);
            tradeDate = tradeDate.plusMonths(3);
        }
        if(marketReq.getParaId() == 3){
            startDate = tradeDate.minusYears(3);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 5){
            startDate = tradeDate.minusYears(5);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 10){
            startDate = tradeDate.minusYears(10);
            tradeDate = tradeDate.plusYears(1);
        }
        if(marketReq.getParaId() == 20){
            startDate = tradeDate.minusYears(20);
            tradeDate = tradeDate.plusYears(1);
        }

        marketReq.setTradeDate(tradeDate.format(formatter));
        marketReq.setStartDate(startDate.format(formatter));

        List<DailyVo> dailyVoList = marketMapper.selectDaily(marketReq);
        Double openFirstDay = dailyVoList.get(0).getOpen();

        List<LogDailyVo> logDailyVoList = new ArrayList<>();

        dailyVoList.stream().forEach(dailyVo -> {
            LogDailyVo logDailyVo = new LogDailyVo();

            BeanUtils.copyProperties(dailyVo,logDailyVo);

            logDailyVo.setLogOpen(Double.parseDouble(String.format("%.4f",Math.log10(10*dailyVo.getOpen()/openFirstDay))));
            logDailyVo.setLogClose(Double.parseDouble(String.format("%.4f",Math.log10(10*dailyVo.getClose()/openFirstDay))));
            logDailyVo.setLogHigh(Double.parseDouble(String.format("%.4f",Math.log10(10*dailyVo.getHigh()/openFirstDay))));
            logDailyVo.setLogLow(Double.parseDouble(String.format("%.4f",Math.log10(10*dailyVo.getLow()/openFirstDay))));

            logDailyVoList.add(logDailyVo);
        });


        LogDailyResp logDailyResp = new LogDailyResp();
        logDailyResp.setTsCode(tsCode);
        logDailyResp.setTsName(marketReq.getName());
        logDailyResp.setLogDailyVoList(logDailyVoList);
        return logDailyResp;
    }

    @Override
    public MoneyFlowResp getMoneyFlow(MarketReq marketReq){
        System.out.println("----money_flow market service----");

        MoneyFlowVo moneyFlowVo = marketMapper.selectMoneyFlow(marketReq);

        //买盘金额
        moneyFlowVo.setBuyElgAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getBuyElgAmount() / 10000)));
        moneyFlowVo.setBuyLgAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getBuyLgAmount() / 10000)));
        moneyFlowVo.setBuyMdAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getBuyMdAmount() / 10000)));
        moneyFlowVo.setBuySmAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getBuySmAmount() / 10000)));

        //卖盘金额
        moneyFlowVo.setSellElgAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getSellElgAmount() / 10000)));
        moneyFlowVo.setSellLgAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getSellLgAmount() / 10000)));
        moneyFlowVo.setSellMdAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getSellMdAmount() / 10000)));
        moneyFlowVo.setSellSmAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getSellSmAmount() / 10000)));

        //资金净流入
        moneyFlowVo.setNetMfAmount(Double.parseDouble(String.format("%.2f",moneyFlowVo.getNetMfAmount() / 10000)));

        MoneyFlowResp moneyFlowResp = new MoneyFlowResp();
        moneyFlowResp.setTsCode(marketReq.getTsCode());
        moneyFlowResp.setTsName(marketReq.getName());
        moneyFlowResp.setTradeDate(marketReq.getTradeDate());
        moneyFlowResp.setMoneyFlowVo(moneyFlowVo);
        return moneyFlowResp;
    }

    @Override
    public FinaMain2Resp getFinaMain2(MarketReq marketReq){
        System.out.println("----fina_main2 market service----");
        FinaMain2Resp finaMain2Resp = marketMapper.selectFinaMain2(marketReq);
        finaMain2Resp.setIdx(1);
        finaMain2Resp.setTotalMv(Double.parseDouble(String.format("%.2f",finaMain2Resp.getTotalMv() / 10000)));
        try{
            //计算净资产
            if(finaMain2Resp.getPb() != 0){
                finaMain2Resp.setAsset(Double.parseDouble(String.format("%.2f",finaMain2Resp.getTotalMv() / finaMain2Resp.getPb())));
            }
        }catch (Exception e){
            System.out.println("pb为0:" + e.getMessage());
        }
        finaMain2Resp.setPb(0.0);
        return finaMain2Resp;
    }

    @Override
    public FinaMain3Resp getFinaMain3(MarketReq marketReq){
        System.out.println("----fina_main3 market service----");
        FinaMain3Resp finaMain3Resp = marketMapper.selectFinaMain3(marketReq);

        finaMain3Resp.setIdx(1);
        finaMain3Resp.setTotalMv(Double.parseDouble(String.format("%.2f",finaMain3Resp.getTotalMv() / 10000)));
        try{
            //计算净资产
            if(finaMain3Resp.getPb() != 0){
                finaMain3Resp.setAsset(Double.parseDouble(String.format("%.2f",finaMain3Resp.getTotalMv() / finaMain3Resp.getPb())));
            }
        }catch (Exception e){
            System.out.println("pb为0:" + e.getMessage());
        }
        finaMain3Resp.setPb(0.0);

        return finaMain3Resp;
    }

    @Override
    public CompanyInfoResp getCompanyInfo(MarketReq marketReq){
        System.out.println("----company_info market service----");
        CompanyInfoResp companyInfoResp = marketMapper.selectCompanyInfo(marketReq);

        companyInfoResp.setIdx(1);
        companyInfoResp.setTotalMv(Double.parseDouble(String.format("%.2f",companyInfoResp.getTotalMv() / 10000)));
        try{
            //计算净资产
            if(companyInfoResp.getPb() != 0){
                companyInfoResp.setAsset(Double.parseDouble(String.format("%.2f",companyInfoResp.getTotalMv() / companyInfoResp.getPb())));
            }
        }catch (Exception e){
            System.out.println("pb为0:" + e.getMessage());
        }
        companyInfoResp.setPb(0.0);

        return companyInfoResp;
    }
}

package com.example.springboot.analysis.service.impl;

import com.example.springboot.analysis.mapper.AnalysisMapper;
import com.example.springboot.analysis.service.AnalysisService;
import com.example.springboot.analysis.vo.req.LimitReq;
import com.example.springboot.analysis.vo.resp.*;
import com.example.springboot.analysis.vo.vo.HfqDailyVo;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.DoubleBuffer;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * author: lizijian
 * desc: 分析 服务实现层
 * date: 2024-09-25
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    AnalysisMapper analysisMapper;

    @Override
    public List<LimitResp> getLimit(LimitReq limitReq){
        System.out.println("----getLimit----");
        List<LimitResp> limitRespList = null;
        try{
            limitRespList = analysisMapper.getLimit(limitReq);
            int i = 1;
            for(LimitResp limitResp : limitRespList){
                limitResp.setIdx(i++);
                limitResp.setPctChg(Double.parseDouble(String.format("%.2f",limitResp.getPctChg())));
                limitResp.setClose(Double.parseDouble(String.format("%.2f",limitResp.getClose())));
                if(limitResp.getPeTtm() != null){
                    limitResp.setPeTtm(Double.parseDouble(String.format("%.2f",limitResp.getPeTtm())));
                }else{
                    limitResp.setPeTtm(0.0);
                }
                if(limitResp.getPb() != null){
                    limitResp.setPb(Double.parseDouble(String.format("%.2f",limitResp.getPb())));
                }else{
                    limitResp.setPb(0.0);
                }
                if(limitResp.getPsTtm() != null){
                    limitResp.setPsTtm(Double.parseDouble(String.format("%.2f",limitResp.getPsTtm())));
                }else{
                    limitResp.setPsTtm(0.0);
                }
                limitResp.setTurnoverRate(Double.parseDouble(String.format("%.2f",limitResp.getTurnoverRate())));
                limitResp.setAmount(Double.parseDouble(String.format("%.2f",limitResp.getAmount() / 100000)));
                limitResp.setTotalMv(Double.parseDouble(String.format("%.2f",limitResp.getTotalMv() / 10000)));
                //计算净资产
                if(limitResp.getPb() != 0){
                    limitResp.setAsset(Double.parseDouble(String.format("%.2f",limitResp.getTotalMv() / limitResp.getPb())));
                }else{
                    limitResp.setAsset(0.0);
                }
                //计算振幅
                limitResp.setAmp(Double.parseDouble(String.format("%.2f",100*(limitResp.getHigh() - limitResp.getLow()) / limitResp.getPreClose())));
                //计算ROE和利润率
                if(limitResp.getPeTtm() != 0 && limitResp.getPeTtm() != null){
                    limitResp.setRoe(Double.parseDouble(String.format("%.2f",limitResp.getPb()/limitResp.getPeTtm())));
                    limitResp.setProfitRate(Double.parseDouble(String.format("%.2f",limitResp.getPsTtm()/limitResp.getPeTtm())));
                }else{
                    limitResp.setRoe(0.0);
                    limitResp.setProfitRate(0.0);
                }
            }
        }catch (Exception e){
            System.out.println("捕获异常:" + e.getMessage());
        }

        return limitRespList;
    }

    @Override
    public List<FinaMianResp> getFinaMain(LimitReq limitReq){
        System.out.println("----getLimitFinaMain----");
        System.out.println(limitReq);
        List<FinaMianResp> finaMianRespList = analysisMapper.getFinaMain(limitReq);
        AtomicInteger index = new AtomicInteger(1);
        finaMianRespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return finaMianRespList;
    }

    @Override
    public List<TopHoldResp> getTopHold(LimitReq limitReq){
        System.out.println("----getTopHold----");
        List<TopHoldResp> topHoldRespList = analysisMapper.getTopHold(limitReq);
        AtomicInteger index = new AtomicInteger(1);
        topHoldRespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return topHoldRespList;
    }

    @Override
    public List<TenDaysMarketResp> getTenDaysMarket(LimitReq limitReq){
        System.out.println("----getAnalysisTenDaysMarket----");
        List<TenDaysMarketResp> tenDaysMarketRespList = analysisMapper.getTenDaysMarket(limitReq);
        AtomicInteger index = new AtomicInteger(1);
        tenDaysMarketRespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
                resp.setYPct1(Double.parseDouble(String.format("%.2f",resp.getYPct1())));
                resp.setYPct2(Double.parseDouble(String.format("%.2f",resp.getYPct2())));
                resp.setYPct3(Double.parseDouble(String.format("%.2f",resp.getYPct3())));
                resp.setYPct4(Double.parseDouble(String.format("%.2f",resp.getYPct4())));
                resp.setYPct5(Double.parseDouble(String.format("%.2f",resp.getYPct5())));
                resp.setYPct6(Double.parseDouble(String.format("%.2f",resp.getYPct6())));
                resp.setYPct7(Double.parseDouble(String.format("%.2f",resp.getYPct7())));
                resp.setYPct8(Double.parseDouble(String.format("%.2f",resp.getYPct8())));
                resp.setYPct9(Double.parseDouble(String.format("%.2f",resp.getYPct9())));

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return tenDaysMarketRespList;
    }

    @Override
    public List<TwoDaysDifferResp> getTwoDaysDiffer(LimitReq limitReq){
        System.out.println("----two_days_differ analysis Service----");
        List<HfqDailyVo> hfqDailyVoList1 = analysisMapper.selectCommon1(limitReq);
        List<HfqDailyVo> hfqDailyVoList2 = analysisMapper.selectCommon2(limitReq);
        List<TwoDaysDifferResp> twoDaysDifferRespList = analysisMapper.selectIndustryMember(limitReq);

        Map<String,HfqDailyVo> hfqDailyVoMap1 = hfqDailyVoList1.stream()
                .collect(Collectors.toMap(HfqDailyVo::getTsCode, vo->vo));

        Map<String,HfqDailyVo> hfqDailyVoMap2 = hfqDailyVoList2.stream()
                .collect(Collectors.toMap(HfqDailyVo::getTsCode,vo->vo));

        twoDaysDifferRespList.stream().forEach(resp->{
            String tsCode = resp.getTsCode();
            resp.setTotalMv1(0.0);
            resp.setTotalMv2(0.0);
            resp.setPctChgTwoDays(0.0);

            HfqDailyVo hfqDailyVo1 = hfqDailyVoMap1.get(tsCode);
            HfqDailyVo hfqDailyVo2 = hfqDailyVoMap2.get(tsCode);

            if(hfqDailyVo1 != null){
                resp.setClose1(hfqDailyVo1.getClose());
                Double totalMv1 = hfqDailyVo1.getTotalMv();
                Double pb1 = hfqDailyVo1.getPb();
                if(totalMv1 != null){
                    resp.setTotalMv1(Double.parseDouble(String.format("%.2f",totalMv1 / 10000)));
                }

                if(totalMv1 != null && pb1 != null && pb1 != 0){
                    resp.setAsset1(Double.parseDouble(String.format("%.2f",totalMv1 / (10000*pb1))));
                }
                resp.setStartDate(hfqDailyVo1.getTradeDate());
            }

            if(hfqDailyVo2 != null){
                resp.setClose2(hfqDailyVo2.getClose());
                Double totalMv2 = hfqDailyVo2.getTotalMv();
                Double pb2 = hfqDailyVo2.getPb();
                if(totalMv2 != null){
                    resp.setTotalMv2(Double.parseDouble(String.format("%.2f",totalMv2 / 10000)));
                }
                if(totalMv2 != null && pb2 != null && pb2 != 0){
                    resp.setAsset2(Double.parseDouble(String.format("%.2f",totalMv2 / (10000*pb2))));
                }
                resp.setTradeDate(hfqDailyVo2.getTradeDate());
            }

            if(hfqDailyVo1 != null && hfqDailyVo2 != null){
                Double close1 = hfqDailyVo1.getClose();
                Double close2 = hfqDailyVo2.getClose();
                if(close1 != null && close2 != null){
                    resp.setPctChgTwoDays(Double.parseDouble(String.format("%.2f",100*(close2 - close1)/close1)));
                }
            }
        });

        twoDaysDifferRespList.sort(Comparator.comparing(TwoDaysDifferResp::getPctChgTwoDays).reversed());
        twoDaysDifferRespList = twoDaysDifferRespList.subList(0,200);

        //按照哪种排序
        if(limitReq.getParaId() == 1){
            twoDaysDifferRespList.sort(Comparator.comparing(TwoDaysDifferResp::getPctChgTwoDays).reversed());
        }
        if(limitReq.getParaId() == 2){
            twoDaysDifferRespList.sort(Comparator.comparing(TwoDaysDifferResp::getIndustryNameL1).thenComparing(TwoDaysDifferResp::getTotalMv2).reversed());
        }


        AtomicInteger idx = new AtomicInteger(1);
        twoDaysDifferRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
        });
        return twoDaysDifferRespList;
    }

    @Override
    public List<CompanyInfoResp> getCompanyInfo(LimitReq limitReq){
        System.out.println("----analysis company_info Service----");
        List<CompanyInfoResp> companyInfoRespList = analysisMapper.getCompanyInfo(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        companyInfoRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("pb为0:" + e.getMessage());
            }
            resp.setPb(0.0);
        });
        return companyInfoRespList;
    }

    @Override
    public List<FinaMain2Resp> getFinaMain2Resp(LimitReq limitReq){
        System.out.println("----analysis fina_main2 Service----");
        List<FinaMain2Resp> finaMain2RespList = analysisMapper.getFinaMain2(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain2RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("pb为0:" + e.getMessage());
            }
            resp.setPb(0.0);
        });
        return finaMain2RespList;
    }

    @Override
    public List<FinaMain3Resp> getFinaMain3Resp(LimitReq limitReq){
        System.out.println("----analysis fina_main3 Servcie-----");
        List<FinaMain3Resp> finaMain3RespList = analysisMapper.getFinaMain3(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain3RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("pb为0:" + e.getMessage());
            }
            resp.setPb(0.0);
        });
        return finaMain3RespList;
    }

    @Override
    public List<Top100Resp> getTop100(LimitReq limitReq){
        System.out.println("----top100 Service----");
        List<Top100Resp> top100RespList = analysisMapper.getTop100(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        top100RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            if(resp.getPctChg() != null && resp.getPctChg() != 0){
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            }

            if(resp.getPeTtm() != null && resp.getPeTtm() != 0){
                resp.setPeTtm(Double.parseDouble(String.format("%.2f",resp.getPeTtm())));
            }

            if(resp.getPsTtm() != null && resp.getPsTtm() != 0){
                resp.setPsTtm(Double.parseDouble(String.format("%.2f",resp.getPsTtm())));
            }

            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setPb(Double.parseDouble(String.format("%.2f",resp.getPb())));
            }

            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }

            if(resp.getTurnoverRate() != null){
                resp.setTurnoverRate(Double.parseDouble(String.format("%.2f",resp.getTurnoverRate())));
            }

            if(resp.getAmount() != null){
                resp.setAmount(Double.parseDouble(String.format("%.2f",resp.getAmount() / 100000)));
            }

            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setAssets(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }

            if(resp.getPeTtm() != null && resp.getPeTtm() != 0 && resp.getPsTtm() != null){
                resp.setProfitRate(Double.parseDouble(String.format("%.2f",resp.getPsTtm() / resp.getPeTtm())));
            }else{
                resp.setProfitRate(0.0);
            }

            if(resp.getPeTtm() != null && resp.getPeTtm() != 0 && resp.getPb() != null){
                resp.setRoe(Double.parseDouble(String.format("%.2f",resp.getPb() / resp.getPeTtm())));
            }else{
                resp.setRoe(0.0);
            }
        });
        return top100RespList;
    }

    @Override
    public List<FiveDaysLimitResp> getFiveDaysLimit(LimitReq limitReq){
        System.out.println("----five_days_limit analysis Service----");
        List<FiveDaysLimitResp> fiveDaysLimitRespList = analysisMapper.selectFiveDaysLimit(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        fiveDaysLimitRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));

            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setAssets(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return fiveDaysLimitRespList;
    }

    @Override
    public List<MoneyFlowResp> getMoneyFlow(LimitReq limitReq){
        System.out.println("----money_flow analysis Service----");
        List<MoneyFlowResp> moneyFlowRespList = analysisMapper.selectMoneyFlow(limitReq);

        AtomicInteger idx = new AtomicInteger(1);
        moneyFlowRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            resp.setAmount(Double.parseDouble(String.format("%.2f",resp.getAmount() / 100000)));

            if(resp.getBuyElgAmount() != null){
                resp.setBuyElgAmount(Double.parseDouble(String.format("%.2f",resp.getBuyElgAmount() / 10000)));
            }

            if(resp.getBuyLgAmount() != null){
                resp.setBuyLgAmount(Double.parseDouble(String.format("%.2f",resp.getBuyLgAmount() / 10000)));
            }
            if(resp.getBuyMdAmount() != null){
                resp.setBuyMdAmount(Double.parseDouble(String.format("%.2f",resp.getBuyMdAmount() / 10000)));
            }
            if(resp.getBuySmAmount() != null){
                resp.setBuySmAmount(Double.parseDouble(String.format("%.2f",resp.getBuySmAmount() / 10000)));
            }

            if(resp.getSellElgAmount() != null){
                resp.setSellElgAmount(Double.parseDouble(String.format("%.2f",resp.getSellElgAmount() / 10000)));
            }
            if(resp.getSellLgAmount() != null){
                resp.setSellLgAmount(Double.parseDouble(String.format("%.2f",resp.getSellLgAmount() / 10000)));
            }
            if(resp.getSellMdAmount() != null){
                resp.setSellMdAmount(Double.parseDouble(String.format("%.2f",resp.getSellMdAmount() / 10000)));
            }
            if(resp.getSellSmAmount() != null){
                resp.setSellSmAmount(Double.parseDouble(String.format("%.2f",resp.getSellSmAmount() / 10000)));
            }

            if(resp.getNetMfAmount() != null){
                resp.setNetMfAmount(Double.parseDouble(String.format("%.2f",resp.getNetMfAmount() / 10000)));
            }

            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setAssets(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return moneyFlowRespList;
    }

    @Override
    public List<MoneyFlowPctResp> getMoneyFlowPct(LimitReq limitReq){
        System.out.println("----money_flow_pct analysis Service----");
        List<MoneyFlowResp> moneyFlowRespList = analysisMapper.selectMoneyFlow(limitReq);

        //计算资金流向百分比
        AtomicInteger idx = new AtomicInteger(1);
        List<MoneyFlowPctResp> moneyFlowPctRespList = moneyFlowRespList.stream().map(moneyFlowResp->{
            MoneyFlowPctResp moneyFlowPctResp = new MoneyFlowPctResp();
            BeanUtils.copyProperties(moneyFlowResp,moneyFlowPctResp);

            moneyFlowPctResp.setIdx(idx.getAndIncrement());
            moneyFlowPctResp.setPctChg(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getPctChg())));

            Double amount = moneyFlowResp.getAmount();
            if(moneyFlowResp.getBuyElgAmount() != null && amount != null){
                moneyFlowPctResp.setBuyElgAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getBuyElgAmount() / amount)));
            }

            if(moneyFlowResp.getBuyLgAmount() != null && amount != null){
                moneyFlowPctResp.setBuyLgAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getBuyLgAmount() / amount)));
            }

            if(moneyFlowResp.getBuyMdAmount() != null && amount != null){
                moneyFlowPctResp.setBuyMdAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getBuyMdAmount() / amount)));
            }

            if(moneyFlowResp.getBuySmAmount() != null && amount != null){
                moneyFlowPctResp.setBuySmAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getBuySmAmount() / amount)));
            }

            if(moneyFlowResp.getSellElgAmount() != null && amount != null){
                moneyFlowPctResp.setSellElgAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getSellElgAmount() / amount)));
            }

            if(moneyFlowResp.getSellLgAmount() != null && amount != null){
                moneyFlowPctResp.setSellLgAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getSellLgAmount() / amount)));
            }

            if(moneyFlowResp.getSellMdAmount() != null && amount != null){
                moneyFlowPctResp.setSellMdAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getSellMdAmount() / amount)));
            }

            if(moneyFlowResp.getSellSmAmount() != null && amount != null){
                moneyFlowPctResp.setSellSmAmountPct(Double.parseDouble(String.format("%.2f",1000*moneyFlowResp.getSellMdAmount() / amount)));
            }

            moneyFlowPctResp.setTotalMv(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getTotalMv() / 10000)));
            moneyFlowPctResp.setAmount(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getAmount() / 100000)));
            moneyFlowPctResp.setTurnoverRate(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getTurnoverRate())));
            if(moneyFlowPctResp.getNetMfAmount() != null){
                moneyFlowPctResp.setNetMfAmount(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getNetMfAmount() / 10000)));
            }

            if(moneyFlowPctResp.getPb() != null && moneyFlowPctResp.getPb() != 0){
                moneyFlowPctResp.setAssets(Double.parseDouble(String.format("%.2f",moneyFlowPctResp.getTotalMv() / moneyFlowPctResp.getPb())));
            }
            return moneyFlowPctResp;
        }).collect(Collectors.toList());
        return moneyFlowPctRespList;
    }

    @Override
    public List<LimitBoardResp> getLimitBoard(LimitReq limitReq){
        System.out.println("----limit_board service----");
        List<LimitBoardResp> limitBoardRespList = analysisMapper.selectLimitBoard(limitReq);

        AtomicInteger idx = new AtomicInteger();
        limitBoardRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
            resp.setPb(0.0);
        });
        return limitBoardRespList;
    }
}

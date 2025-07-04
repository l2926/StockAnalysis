package com.example.springboot.industry.service.impl;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TwoDaysDifferResp;
import com.example.springboot.analysis.vo.vo.HfqDailyVo;
import com.example.springboot.industry.mapper.IndustryMapper;
import com.example.springboot.industry.service.IndustryService;
import com.example.springboot.industry.vo.req.IndustryReq;
import com.example.springboot.industry.vo.resp.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: lizijian
 * @desc: 行业Service 实现类
 * @date: 2024-09-25
 */

@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    IndustryMapper industryMapper;

    @Override
    public List<FundmentalResp> getFundmental(IndustryReq industryReq){
        System.out.println("----getFundmental----");
        List<FundmentalResp> fundmentalRespList = null;
        try{
            fundmentalRespList = industryMapper.getFundmental(industryReq);
            int i = 1;
            for(FundmentalResp fundmentalResp : fundmentalRespList){
                fundmentalResp.setIdx(i++);
                fundmentalResp.setPctChg(Double.parseDouble(String.format("%.2f",fundmentalResp.getPctChg())));
                fundmentalResp.setClose(Double.parseDouble(String.format("%.2f",fundmentalResp.getClose())));
                if(fundmentalResp.getPeTtm() != null){
                    fundmentalResp.setPeTtm(Double.parseDouble(String.format("%.2f",fundmentalResp.getPeTtm())));
                }else{
                    fundmentalResp.setPeTtm(0.0);
                }
                if(fundmentalResp.getPb() != null){
                    fundmentalResp.setPb(Double.parseDouble(String.format("%.2f",fundmentalResp.getPb())));
                }else{
                    fundmentalResp.setPb(0.0);
                }
                if(fundmentalResp.getPsTtm() != null){
                    fundmentalResp.setPsTtm(Double.parseDouble(String.format("%.2f",fundmentalResp.getPsTtm())));
                }else{
                    fundmentalResp.setPsTtm(0.0);
                }
                fundmentalResp.setTurnoverRate(Double.parseDouble(String.format("%.2f",fundmentalResp.getTurnoverRate())));
                fundmentalResp.setAmount(Double.parseDouble(String.format("%.2f",fundmentalResp.getAmount() / 100000)));
                fundmentalResp.setTotalMv(Double.parseDouble(String.format("%.2f",fundmentalResp.getTotalMv() / 10000)));
                //计算净资产
                if(fundmentalResp.getPb() != 0){
                    fundmentalResp.setAsset(Double.parseDouble(String.format("%.2f",fundmentalResp.getTotalMv() / fundmentalResp.getPb())));
                }
                //计算振幅
                fundmentalResp.setAmp(Double.parseDouble(String.format("%.2f",100*(fundmentalResp.getHigh() - fundmentalResp.getLow()) / fundmentalResp.getPreClose())));
                //计算ROE和利润率
                if(fundmentalResp.getPeTtm() != 0 && fundmentalResp.getPeTtm() != null){
                    fundmentalResp.setRoe(Double.parseDouble(String.format("%.2f",fundmentalResp.getPb()/fundmentalResp.getPeTtm())));
                    fundmentalResp.setProfitRate(Double.parseDouble(String.format("%.2f",fundmentalResp.getPsTtm()/fundmentalResp.getPeTtm())));
                }else{
                    fundmentalResp.setRoe(0.0);
                    fundmentalResp.setProfitRate(0.0);
                }
                //计算营/资比
                if(fundmentalResp.getPsTtm() != 0){
                    fundmentalResp.setRevAssetRatio(Double.parseDouble(String.format("%.2f",fundmentalResp.getPb() / fundmentalResp.getPsTtm())));
                }else{
                    fundmentalResp.setRevAssetRatio(0.0);
                }
            }
        }catch (Exception e){
            System.out.println("捕获异常:" + e.getMessage());
        }
        return fundmentalRespList;
    }
    @Override
    public List<FinaMainResp> getFinaMain(IndustryReq industryReq){
        System.out.println("----getFinaMain----");
        List<FinaMainResp> finaMainRespList = null;

        finaMainRespList = industryMapper.getFinaMain(industryReq);
        int i = 1;
        for(FinaMainResp finaMainResp : finaMainRespList){
            finaMainResp.setIdx(i++);
            finaMainResp.setTotalMv(Double.parseDouble(String.format("%.2f",finaMainResp.getTotalMv() / 10000)));
            finaMainResp.setPctChg(Double.parseDouble(String.format("%.2f",finaMainResp.getPctChg())));
            try{
                //计算净资产
                if(finaMainResp.getPb() != 0){
                    finaMainResp.setAsset(Double.parseDouble(String.format("%.2f",finaMainResp.getTotalMv() / finaMainResp.getPb())));
                }
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        }
        return finaMainRespList;
    }

    @Override
    public List<TopHoldResp> getTopHold(IndustryReq industryReq){
        System.out.println("----getTopHold----");
        List<TopHoldResp> topHoldRespList = industryMapper.getTopHold(industryReq);
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
    public List<TenDaysMarketResp> getTenDaysMarket(IndustryReq industryReq){
        System.out.println("----getIndustryTenDaysMarket----");
        List<TenDaysMarketResp> tenDaysMarketRespList = industryMapper.getTenDaysMarket(industryReq);
        AtomicInteger index = new AtomicInteger(1);
        tenDaysMarketRespList.stream().forEach(resp -> {
            resp.setIdx(index.getAndIncrement());

            if(resp.getPctChg() != null){
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            }

            if(resp.getYPct1() != null){
                resp.setYPct1(Double.parseDouble(String.format("%.2f",resp.getYPct1())));
            }

            if(resp.getYPct2() != null){
                resp.setYPct2(Double.parseDouble(String.format("%.2f",resp.getYPct2())));
            }

            if(resp.getYPct3() != null){
                resp.setYPct3(Double.parseDouble(String.format("%.2f",resp.getYPct3())));
            }

            if(resp.getYPct4() != null){
                resp.setYPct4(Double.parseDouble(String.format("%.2f",resp.getYPct4())));
            }

            if(resp.getYPct5() != null){
                resp.setYPct5(Double.parseDouble(String.format("%.2f",resp.getYPct5())));
            }

            if(resp.getYPct6() != null){
                resp.setYPct6(Double.parseDouble(String.format("%.2f",resp.getYPct6())));
            }

            if(resp.getYPct7() != null){
                resp.setYPct7(Double.parseDouble(String.format("%.2f",resp.getYPct7())));
            }
            if(resp.getYPct8() != null){
                resp.setYPct8(Double.parseDouble(String.format("%.2f",resp.getYPct8())));
            }
            if(resp.getYPct9() != null){
                resp.setYPct9(Double.parseDouble(String.format("%.2f",resp.getYPct9())));
            }

            //计算市值
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            //计算净资产
            if(resp.getPb() != null && resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return tenDaysMarketRespList;
    }

    @Override
    public List<TwoDaysDifferResp> getTwoDaysDiffer(IndustryReq industryReq){
        System.out.println("----getTwoDays industry Service----");
        List<HfqDailyVo> hfqDailyVoList1 = industryMapper.selectCommon1(industryReq);
        List<HfqDailyVo> hfqDailyVoList2 = industryMapper.selectCommon2(industryReq);
        List<TwoDaysDifferResp> twoDaysDifferRespList = industryMapper.selectIndustryMember(industryReq);

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

        //按照哪种排序
        if(industryReq.getParaId() == 1){
            twoDaysDifferRespList.sort(Comparator.comparing(TwoDaysDifferResp::getPctChgTwoDays).reversed());
        }
        if(industryReq.getParaId() == 2){
            twoDaysDifferRespList.sort(Comparator.comparing(TwoDaysDifferResp::getIndustryNameL1).thenComparing(TwoDaysDifferResp::getTotalMv2).reversed());
        }


        AtomicInteger idx = new AtomicInteger(1);
        twoDaysDifferRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
        });

        return twoDaysDifferRespList;
    }

    @Override
    public List<CompanyInfoResp> getCompanyInfo(IndustryReq industryReq){
        System.out.println("----company_info Service----");
        List<CompanyInfoResp> companyInfoRespList = industryMapper.getCompanyInfo(industryReq);

        AtomicInteger idx = new AtomicInteger(1);
        companyInfoRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != 0){
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
    public List<FinaMain2Resp> getFinaMain2(IndustryReq industryReq){
        System.out.println("----getFinaMain2 Service----");
        List<FinaMain2Resp> finaMain2RespList = industryMapper.getFinaMain2(industryReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain2RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != 0){
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
    public List<FinaMain3Resp> getFinaMain3(IndustryReq industryReq){
        System.out.println("----getFinaMain3 Service----");
        List<FinaMain3Resp> finaMain3RespList = industryMapper.getFinaMain3(industryReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain3RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            try{
                //计算净资产
                if(resp.getPb() != 0){
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
    public List<FiveDaysLimitResp> getFiveDaysLimit(IndustryReq industryReq){
        System.out.println("----five_days_limit industry Service----");
        List<FiveDaysLimitResp> fiveDaysLimitRespList = industryMapper.selectFiveDaysLimit(industryReq);

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
    public List<MoneyFlowResp> getMoneyFlow(IndustryReq industryReq){
        System.out.println("----money_flow industry Service----");
        List<MoneyFlowResp> moneyFlowRespList = industryMapper.selectMoneyFlow(industryReq);

        AtomicInteger idx = new AtomicInteger(1);
        moneyFlowRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            resp.setAmount(Double.parseDouble(String.format("%.2f",resp.getAmount() / 100000)));
            resp.setTurnoverRate(Double.parseDouble(String.format("%.2f",resp.getTurnoverRate())));

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
    public List<MoneyFlowPctResp> getMoneyFlowPct(IndustryReq industryReq){
        System.out.println("----money_flow_pct industry Service----");
        List<MoneyFlowResp> moneyFlowRespList = industryMapper.selectMoneyFlow(industryReq);

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
}

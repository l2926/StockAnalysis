package com.example.springboot.index.service.impl;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.mapper.IndexMapper;
import com.example.springboot.index.service.IndexService;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.index.vo.vo.DailyVo;
import com.example.springboot.index.vo.vo.StatisticCommon;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Ssl;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexMapper indexMapper;
    @Override
    public DailyResp getDaily(IndexReq indexReq){
        System.out.println("----getDailyService----");
        System.out.println(indexReq.getIndexCode());
        String[] tmp = indexReq.getIndexCode().split("\\.");
        //System.out.println(tmp[0]);
        indexReq.setSymbol(tmp[0]);
        List<DailyVo> dailyVoList = indexMapper.selectDaily(indexReq);

        //指数行情格式转换
        Double openFirstDay = dailyVoList.get(0).getOpen();
        dailyVoList.stream().forEach(vo->{
            String symbol = indexReq.getSymbol();
            if(!symbol.equals("000001") && !symbol.equals("399001") && !symbol.equals("399005") && !symbol.equals("399006")){
                vo.setOpen(Double.parseDouble(String.format("%.2f",1000*vo.getOpen()/openFirstDay)));
                vo.setClose(Double.parseDouble(String.format("%.2f",1000*vo.getClose()/openFirstDay)));
                vo.setHigh(Double.parseDouble(String.format("%.2f",1000*vo.getHigh()/openFirstDay)));
                vo.setLow(Double.parseDouble(String.format("%.2f",1000*vo.getLow()/openFirstDay)));

                if(vo.getTotalMv() != null){
                    vo.setTotalMv(Double.parseDouble(String.format("%.2f",vo.getTotalMv()/10000)));
                }
                if(vo.getAmount() != null){
                    vo.setAmount(Double.parseDouble(String.format("%.2f",vo.getAmount()/10000)));
                }
            }else{
                vo.setOpen(Double.parseDouble(String.format("%.2f",vo.getOpen())));
                vo.setClose(Double.parseDouble(String.format("%.2f",vo.getClose())));
                vo.setHigh(Double.parseDouble(String.format("%.2f",vo.getHigh())));
                vo.setLow(Double.parseDouble(String.format("%.2f",vo.getLow())));

                if(vo.getTotalMv() != null){
                    vo.setTotalMv(Double.parseDouble(String.format("%.2f",vo.getTotalMv()/100000)));
                }
                if(vo.getAmount() != null){
                    vo.setAmount(Double.parseDouble(String.format("%.2f",vo.getAmount()/100000)));
                }
            }
        });

        //封装数据
        DailyResp dailyResp = new DailyResp();
        dailyResp.setIndexName(indexReq.getIndexName());
        dailyResp.setIndexCode(indexReq.getIndexCode());
        dailyResp.setDailyVoList(dailyVoList);
        return dailyResp;
    }

    @Override
    public List<StatisticResp> getStatistics(StatisticsReq statisticsReq){
        System.out.println("----getStatistics Service----");
        List<StatisticCommon> statisticCommonList = indexMapper.selectAllCommon(statisticsReq);
        List<StatisticResp> statisticRespList = indexMapper.selectSwAllIndustry(statisticsReq);

        Map<String,StatisticResp> statisticRespMap = statisticRespList.stream()
                .collect(Collectors.toMap(StatisticResp::getIndexCode,resp->resp));

        //组合每日行业统计信息
        statisticCommonList.stream().forEach(common->{
            String indexCode = "";
            if(statisticsReq.getLevel().equals("L1")){
                indexCode = common.getIndexCodeL1();
            }

            if(statisticsReq.getLevel().equals("L2")){
                indexCode = common.getIndexCodeL2();
            }

            if(statisticsReq.getLevel().equals("L3")){
                indexCode = common.getIndexCodeL3();
            }

            if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                if(statisticRespMap.get(indexCode).getAllCount() == null){
                    statisticRespMap.get(indexCode).setAllCount(0);
                }
                statisticRespMap.get(indexCode).setAllCount(statisticRespMap.get(indexCode).getAllCount() + 1);
            }

            if(common.getPctChg() > 9){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode).getUpCount() == null){
                        statisticRespMap.get(indexCode).setUpCount(0);
                    }
                    statisticRespMap.get(indexCode).setUpCount(statisticRespMap.get(indexCode).getUpCount() + 1);
                }
            }

            if(common.getPctChg() < -9){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode).getDownCount() == null){
                        statisticRespMap.get(indexCode).setDownCount(0);
                    }
                    statisticRespMap.get(indexCode).setDownCount(statisticRespMap.get(indexCode).getDownCount() + 1);
                }
            }
        });

        return statisticRespList;
    }

    @Override
    public List<StatisticAllResp> getStatisticsAll(StatisticsReq statisticsReq){
        System.out.println("----statistics_all service----");
        List<StatisticCommon> statisticCommonList = indexMapper.selectAllCommon(statisticsReq);
        List<StatisticAllResp> statisticAllRespList = indexMapper.selectSwAllIndustry2(statisticsReq);

        Map<String,StatisticAllResp> statisticAllRespMap = statisticAllRespList.stream()
                .collect(Collectors.toMap(StatisticAllResp::getIndexCode,resp->resp));

        statisticCommonList.stream().forEach(common->{
            String indexCode = "";
            if(statisticsReq.getLevel().equals("L1")){
                indexCode = common.getIndexCodeL1();
            }

            if(statisticsReq.getLevel().equals("L2")){
                indexCode = common.getIndexCodeL2();
            }

            if(statisticsReq.getLevel().equals("L3")){
                indexCode = common.getIndexCodeL3();
            }

            if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                if(statisticAllRespMap.get(indexCode).getCount() == null){
                    statisticAllRespMap.get(indexCode).setCount(0);
                }

                if(statisticAllRespMap.get(indexCode).getTotalMv2() == null){
                    statisticAllRespMap.get(indexCode).setTotalMv2(0.0);
                }

                statisticAllRespMap.get(indexCode).setCount(statisticAllRespMap.get(indexCode).getCount() + 1);
                statisticAllRespMap.get(indexCode).setTotalMv2(statisticAllRespMap.get(indexCode).getTotalMv2() + common.getTotalMv());
            }

        });

        statisticAllRespList.stream().forEach(resp->{
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
//            resp.setTotalMv2(Double.parseDouble(String.format("%.2f",resp.getTotalMv2() / 10000)));
            resp.setTotalMv2(0.0);
        });

        return statisticAllRespList;
    }

    @Override
    public List<KplConceptResp> getKplConcept(StatisticsReq statisticsReq){
        System.out.println("----kpl_concept service----");
        List<KplConceptResp> kplConceptRespList = indexMapper.selectKplConcept(statisticsReq);
        return kplConceptRespList;
    }

    @Override
    public List<KplConceptConsResp> getKplConceptCons(ConceptMemberReq conceptMemberReq){
        System.out.println("----kpl_concept_cons service----");
        List<KplConceptConsResp> kplConceptConsRespList = indexMapper.selectKplConceptCons(conceptMemberReq);

        AtomicInteger idx = new AtomicInteger(1);
        kplConceptConsRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            if(resp.getPctChg() != null){
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
            }

            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }

            if(resp.getTotalMv() != null && resp.getPb() != null && resp.getPb() != 0){
                resp.setAssets(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }else{
                resp.setAssets(0.0);
            }
        });
        return kplConceptConsRespList;
    }

    @Override
    public List<DcIndexResp> getDcIndex(StatisticsReq statisticsReq){
        System.out.println("----dc_index service----");
        List<DcIndexResp> dcIndexRespList = indexMapper.selectDcIndex(statisticsReq);
        return dcIndexRespList;
    }

    @Override
    public List<DcMemberResp> getDcMember(ConceptMemberReq conceptMemberReq){
        System.out.println("----dc_member service----");
        List<DcMemberResp> dcMemberRespList = indexMapper.selectDcMember(conceptMemberReq);
        return dcMemberRespList;
    }

    @Override
    public List<FinaMain2Resp> getFinaMain2(ConceptMemberReq conceptMemberReq){
        System.out.println("----fina_main2 service----");
        List<FinaMain2Resp> finaMain2RespList = indexMapper.selectFinaMain2(conceptMemberReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain2RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }

            if(resp.getTotalMv() != null && resp.getPb() != null && resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return finaMain2RespList;
    }

    @Override
    public List<FinaMain3Resp> getFinaMain3(ConceptMemberReq conceptMemberReq){
        System.out.println("----fina_main3 service----");
        List<FinaMain3Resp> finaMain3RespList = indexMapper.selectFinaMain3(conceptMemberReq);

        AtomicInteger idx = new AtomicInteger(1);
        finaMain3RespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }

            if(resp.getTotalMv() != null && resp.getPb() != null && resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return finaMain3RespList;
    }

    @Override
    public List<CompanyInfoResp> getCompanyInfo(ConceptMemberReq conceptMemberReq){
        System.out.println("----company_info service----");
        List<CompanyInfoResp> companyInfoRespList = indexMapper.selectCompanyInfo(conceptMemberReq);

        AtomicInteger idx = new AtomicInteger(1);
        companyInfoRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }

            if(resp.getTotalMv() != null && resp.getPb() != null && resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }
        });
        return companyInfoRespList;
    }

    @Override
    public List<FiveDaysLimitResp> getFiveDaysLimit(ConceptMemberReq conceptMemberReq){
        System.out.println("----five_days_limit concept_member servcie----");
        List<FiveDaysLimitResp> fiveDaysLimitRespList = indexMapper.selectFiveDaysLimit(conceptMemberReq);

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
    public List<MoneyFlowResp> getMoneyFlow(ConceptMemberReq req){
        System.out.println("----money_flow concept_member service----");
        List<MoneyFlowResp> moneyFlowRespList = indexMapper.selectMoneyFlow(req);

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
    public List<MoneyFlowPctResp> getMoneyFlowPct(ConceptMemberReq req){
        System.out.println("----money_flow_pct concept_member service----");

        List<MoneyFlowResp> moneyFlowRespList = indexMapper.selectMoneyFlow(req);

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
    public List<TenDaysMarketResp> getTenDays(ConceptMemberReq req){
        System.out.println("----ten_days concept_member service----");
        List<TenDaysMarketResp> tenDaysMarketRespList = indexMapper.selectTenDaysMarket(req);
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
}

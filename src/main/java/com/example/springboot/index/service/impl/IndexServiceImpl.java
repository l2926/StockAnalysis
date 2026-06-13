package com.example.springboot.index.service.impl;

import com.example.springboot.analysis.vo.resp.FiveDaysLimitResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowPctResp;
import com.example.springboot.analysis.vo.resp.MoneyFlowResp;
import com.example.springboot.analysis.vo.resp.TenDaysMarketResp;
import com.example.springboot.index.vo.req.ConceptMemberReq;
import com.example.springboot.index.vo.req.DcMemberReq;
import com.example.springboot.index.vo.resp.*;
import com.example.springboot.index.mapper.IndexMapper;
import com.example.springboot.index.service.IndexService;
import com.example.springboot.index.vo.req.IndexReq;
import com.example.springboot.index.vo.req.StatisticsReq;
import com.example.springboot.index.vo.vo.*;
import com.example.springboot.industry.vo.resp.CompanyInfoResp;
import com.example.springboot.industry.vo.resp.FinaMain2Resp;
import com.example.springboot.industry.vo.resp.FinaMain3Resp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//                System.out.println(indexCode);
                if(statisticRespMap.get(indexCode) != null){
                    if(statisticRespMap.get(indexCode).getAllCount() == null){
                        statisticRespMap.get(indexCode).setAllCount(0);
                    }
                    statisticRespMap.get(indexCode).setAllCount(statisticRespMap.get(indexCode).getAllCount() + 1);
                }
            }

            Double upLimit = statisticsReq.getParaId().doubleValue();
            Double downLimit = -1*statisticsReq.getParaId().doubleValue();

            if(common.getPctChg() > upLimit){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode) != null){
                        if(statisticRespMap.get(indexCode).getUpCount() == null){
                            statisticRespMap.get(indexCode).setUpCount(0);
                        }
                        statisticRespMap.get(indexCode).setUpCount(statisticRespMap.get(indexCode).getUpCount() + 1);
                    }
                }
            }

            if(common.getPctChg() < downLimit){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode) != null){
                        if(statisticRespMap.get(indexCode).getDownCount() == null){
                            statisticRespMap.get(indexCode).setDownCount(0);
                        }
                        statisticRespMap.get(indexCode).setDownCount(statisticRespMap.get(indexCode).getDownCount() + 1);
                    }
                }
            }
        });

        return statisticRespList;
    }

    @Override
    public StatisticsCountResp getStatisticsCount(StatisticsReq statisticsReq){
        System.out.println("----statistics_count service----");
        statisticsReq.setUpLimit(statisticsReq.getParaId());
        statisticsReq.setDownLimit(-1*statisticsReq.getParaId());

        Integer up = indexMapper.selectStatisticsCountUp(statisticsReq);
        Integer down = indexMapper.selectStatisticsCountDown(statisticsReq);

        Double shPct = indexMapper.selectShPct(statisticsReq);
        Double szPct = indexMapper.selectSzPct(statisticsReq);
        Double smallPct = indexMapper.selectSmallPct(statisticsReq);
        Double startUpPct = indexMapper.selectStartUpPct(statisticsReq);

        //构造返回出参
        StatisticsCountResp statisticsCountResp = new StatisticsCountResp();
        statisticsCountResp.setUpCount(up);
        statisticsCountResp.setDownCount(down);

        statisticsCountResp.setShPct(shPct);
        statisticsCountResp.setSzPct(szPct);
        statisticsCountResp.setSmallPct(smallPct);
        statisticsCountResp.setStartupPct(startUpPct);

        return statisticsCountResp;
    }

    @Override
    public List<StatisticResp> getStatisticsMv(StatisticsReq statisticsReq){
        System.out.println("----statistics_mv service----");

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
//                System.out.println(indexCode);
                if(statisticRespMap.get(indexCode) != null){
                    if(statisticRespMap.get(indexCode).getAllMv() == null){
                        statisticRespMap.get(indexCode).setAllMv(0.0);
                    }
                    statisticRespMap.get(indexCode).setAllMv(statisticRespMap.get(indexCode).getAllMv() + common.getTotalMv());
                }
            }

            Double upLimit = statisticsReq.getParaId().doubleValue();
            Double downLimit = -1*statisticsReq.getParaId().doubleValue();

            if(common.getPctChg() > upLimit){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode) != null){
                        if(statisticRespMap.get(indexCode).getUpMv() == null){
                            statisticRespMap.get(indexCode).setUpMv(0.0);
                        }
                        statisticRespMap.get(indexCode).setUpMv(statisticRespMap.get(indexCode).getUpMv() + common.getTotalMv());
                    }
                }
            }

            if(common.getPctChg() < downLimit){
                if(!indexCode.isEmpty()){
//                System.out.println("----");
//                System.out.println(indexCodeL1);
                    if(statisticRespMap.get(indexCode) != null){
                        if(statisticRespMap.get(indexCode).getDownMv() == null){
                            statisticRespMap.get(indexCode).setDownMv(0.0);
                        }
                        statisticRespMap.get(indexCode).setDownMv(statisticRespMap.get(indexCode).getDownMv() + common.getTotalMv());
                    }
                }
            }
        });

        statisticRespList.stream().forEach(resp->{
            if(resp.getAllMv() != null){
                resp.setAllMv(Double.parseDouble(String.format("%.2f",resp.getAllMv()/10000)));
            }
            if(resp.getUpMv() != null){
                resp.setUpMv(Double.parseDouble(String.format("%.2f",resp.getUpMv()/10000)));
            }
            if(resp.getDownMv() != null){
                resp.setDownMv(Double.parseDouble(String.format("%.2f",resp.getDownMv()/10000)));
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
                if(statisticAllRespMap.get(indexCode) != null){
                    if(statisticAllRespMap.get(indexCode).getCount() == null){
                        statisticAllRespMap.get(indexCode).setCount(0);
                    }

                    if(statisticAllRespMap.get(indexCode).getTotalMv() == null){
                        statisticAllRespMap.get(indexCode).setTotalMv(0.0);
                    }

                    statisticAllRespMap.get(indexCode).setCount(statisticAllRespMap.get(indexCode).getCount() + 1);
                    statisticAllRespMap.get(indexCode).setTotalMv(statisticAllRespMap.get(indexCode).getTotalMv() + common.getTotalMv());

                }
            }

        });

        statisticAllRespList.stream().forEach(resp->{
//            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            if(resp.getTotalMv() != null){
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            }
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

    @Override
    public List<StatisticsAllExcelResp> getStatisticsAllExcel(IndexReq indexReq){
        System.out.println("----statistics_all_excel service----");
        indexMapper.setSqlMode();
        List<StatisticsAllExcelResp> statisticsAllExcelRespList = indexMapper.selectStatisticsAllExcel(indexReq);

        AtomicInteger index = new AtomicInteger(1);
        statisticsAllExcelRespList.stream().filter(resp-> resp.getTradeDate() != null).forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setAmt(Double.parseDouble(String.format("%.2f",resp.getAmt() / 100000)));
                resp.setMv(Double.parseDouble(String.format("%.2f",resp.getMv() / 10000)));
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getAsset() / 10000)));
                resp.setIncome(Double.parseDouble(String.format("%.2f",resp.getIncome() / 10000)));
                resp.setProfit(Double.parseDouble(String.format("%.2f",resp.getProfit() / 10000)));
                resp.setAvgMv(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getCt())));

                resp.setPe(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getProfit())));
                resp.setPb(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getAsset())));
                resp.setPs(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getIncome())));

                resp.setRoe(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getAsset())));
                resp.setProfitRate(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getIncome())));
                resp.setTurnover(Double.parseDouble(String.format("%.2f",resp.getTurnover())));
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return statisticsAllExcelRespList;
    }

    @Override
    public List<StatisticsExcelResp> getStatisticsExcel(IndexReq indexReq){
        System.out.println("----statistics_excel service----");
        indexMapper.setSqlMode();
        List<StatisticsExcelResp> statisticsExcelRespList = indexMapper.selectStatisticsExcel(indexReq);
        List<StatisticsExcelVo> statisticsExcelVoList = indexMapper.selectStatisticsExcel2(indexReq);
        List<ShenWanDailyVo> shenWanDailyVoList = indexMapper.selectShenWanDaily(indexReq);
        Map<String,ShenWanDailyVo> stringShenWanDailyVoMap = shenWanDailyVoList.stream()
                .collect(Collectors.toMap(ShenWanDailyVo::getName,vo->vo));

        Map<String,StatisticsExcelVo> stringStatisticsExcelVoMap = null;
        if(indexReq.getSelectId() == 1){
            if(indexReq.getLevel().equals("market")){
                stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                        .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL1,vo->vo));
            }
            if(indexReq.getLevel().equals("L1")){
                stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                        .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL3,vo->vo));
            }
            if(indexReq.getLevel().equals("area")){
                stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                        .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL2,vo->vo));
            }
        }
        if(indexReq.getSelectId() == 2){
            if(indexReq.getLevel().equals("market")){

            }
            if(indexReq.getLevel().equals("L1")){
                stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                        .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL2,vo->vo));
            }
            if(indexReq.getLevel().equals("area")){
                stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                        .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL1,vo->vo));
            }

        }

//        Map<String,StatisticsExcelVo> stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
//                .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL1,vo->vo));

        int i = 1;
        for (StatisticsExcelResp resp : statisticsExcelRespList){
            try{
                resp.setIdx(i++);
                Integer allCt = 0;
                Double allMv = 0.0;
                Double allAmt = 0.0;

                Double pctChg = 0.0;

                if(indexReq.getSelectId() == 1){
                    if(indexReq.getLevel().equals("market")){
                        allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllCt();
                        allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllMv();
                        allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllAmt();
                        pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL1()).getPctChange();
                    }
                    if(indexReq.getLevel().equals("L1")){
                        allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllCt();
                        allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllMv();
                        allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllAmt();
                        pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL3()).getPctChange();
                    }
                    if(indexReq.getLevel().equals("area")){
                        allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllCt();
                        allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllMv();
                        allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllAmt();
                    }
                }
                if(indexReq.getSelectId() == 2){
                    if(indexReq.getLevel().equals("market")){
                        allCt = statisticsExcelVoList.get(0).getAllCt();
                        allMv = statisticsExcelVoList.get(0).getAllMv();
                        allAmt = statisticsExcelVoList.get(0).getAllAmt();
                    }
                    if(indexReq.getLevel().equals("L1")){
                        allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllCt();
                        allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllMv();
                        allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllAmt();
                        pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL2()).getPctChange();
                    }
                    if(indexReq.getLevel().equals("area")){
                        allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllCt();
                        allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllMv();
                        allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllAmt();
                    }
                }
                resp.setAllCt(allCt);
                resp.setAllMv(allMv);
                resp.setAllAmt(allAmt);
                resp.setPctChg(pctChg);

                resp.setMv(Double.parseDouble(String.format("%.2f",resp.getMv() / 10000)));
                resp.setAllMv(Double.parseDouble(String.format("%.2f",resp.getAllMv() / 10000)));
                resp.setAmt(Double.parseDouble(String.format("%.2f",resp.getAmt() / 100000)));
                resp.setAllAmt(Double.parseDouble(String.format("%.2f",resp.getAllAmt() / 100000)));

                resp.setCtPct(Double.parseDouble(String.format("%.2f",resp.getCt().doubleValue() / resp.getAllCt().doubleValue())));
                resp.setMvPct(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getAllMv())));
                resp.setAmtPct(Double.parseDouble(String.format("%.2f",resp.getAmt() / resp.getAllAmt())));

                //换手率
                resp.setTurnover(Double.parseDouble(String.format("%.2f",resp.getAmt() / resp.getMv())));
                resp.setAllTurnover(Double.parseDouble(String.format("%.2f",resp.getAllAmt() / resp.getAllMv())));

            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        }

        return statisticsExcelRespList;
    }

    @Override
    public List<StatisticsAreaExcelResp> getStatisticsAreaExcel(IndexReq indexReq){
        System.out.println("----statistics_area_excel service----");
        indexMapper.setSqlMode();
        List<StatisticsAreaExcelResp> statisticsAreaExcelRespList = indexMapper.selectStatisticsAreaExcel(indexReq);

        AtomicInteger index = new AtomicInteger(1);
        statisticsAreaExcelRespList.stream().filter(resp-> resp.getTradeDate() != null).forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setAmt(Double.parseDouble(String.format("%.2f",resp.getAmt() / 100000)));
                resp.setMv(Double.parseDouble(String.format("%.2f",resp.getMv() / 10000)));
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getAsset() / 10000)));
                resp.setIncome(Double.parseDouble(String.format("%.2f",resp.getIncome() / 10000)));
                resp.setProfit(Double.parseDouble(String.format("%.2f",resp.getProfit() / 10000)));
                resp.setAvgMv(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getCt())));

                resp.setPe(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getProfit())));
                resp.setPb(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getAsset())));
                resp.setPs(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getIncome())));

                resp.setRoe(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getAsset())));
                resp.setProfitRate(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getIncome())));
                resp.setTurnover(Double.parseDouble(String.format("%.2f",resp.getTurnover())));
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return statisticsAreaExcelRespList;
    }

    @Override
    public HsgtDailyResp getHsgtOverview(IndexReq indexReq){
        System.out.println("----hsgt_daily service----");
        List<HsgtDailyVo> hsgtDailyVoList = indexMapper.selectHsgtDaily(indexReq);

        HsgtDailyResp hsgtOverviewResp = new HsgtDailyResp();
        hsgtOverviewResp.setIndexName("北上资金");
        hsgtOverviewResp.setIndexCode("000001.SH");
        hsgtOverviewResp.setHsgtDailyVoList(hsgtDailyVoList);
        return hsgtOverviewResp;
    }

    @Override
    public List<DcIndex2Resp> getDcIndex2(IndexReq indexReq){
        System.out.println("----dc_index2 service----");
        List<DcIndex2Resp> dcIndex2RespList = indexMapper.selectDcIndex2(indexReq);

        AtomicInteger index = new AtomicInteger(1);
        dcIndex2RespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            resp.setMainChange(Double.parseDouble(String.format("%.2f",resp.getMainChange() / 100000000)));
        });
        return dcIndex2RespList;
    }

    @Override
    public List<DcMember2Resp> getDcMember2(DcMemberReq dcMemberReq){
        System.out.println("----dc_member service----");
        List<DcMember2Resp> dcMember2RespList = indexMapper.selectDcMember2(dcMemberReq);

        AtomicInteger index = new AtomicInteger(1);
        dcMember2RespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }

        });
        return dcMember2RespList;
    }

    @Override
    public List<LimitCptListResp> getLimitCptList(IndexReq indexReq){
        System.out.println("----limit_cpt_list Service----");
        List<LimitCptListResp> limitCptListRespList = indexMapper.selectLimitCptList(indexReq);
        AtomicInteger idx = new AtomicInteger(1);
        limitCptListRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());
            resp.setPctChg(Double.parseDouble(String.format("%.2f",resp.getPctChg())));
        });
        return limitCptListRespList;
    }

    @Override
    public List<StatisticsExcelResp> getStatisticsLevelExcel(IndexReq indexReq){
        System.out.println("----statistics_level_excel service-----");

        indexMapper.setSqlMode();
        List<StatisticsExcelResp> statisticsExcelRespList = indexMapper.selectLevelStatisticsExcel(indexReq);
        List<StatisticsExcelVo> statisticsExcelVoList = indexMapper.selectLevelStatisticsExcel2(indexReq);
        List<ShenWanDailyVo> shenWanDailyVoList = indexMapper.selectLevelShenWanDaily(indexReq);

        Map<String,ShenWanDailyVo> stringShenWanDailyVoMap = shenWanDailyVoList.stream()
                .collect(Collectors.toMap(ShenWanDailyVo::getName,vo->vo));

        Map<String,StatisticsExcelVo> stringStatisticsExcelVoMap = null;

//        if(indexReq.getLevel().equals("market")){
//            stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
//                    .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL1,vo->vo));
//        }

        if(indexReq.getLevel().equals("L1")){
            stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                    .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL1,vo->vo));
        }

        if(indexReq.getLevel().equals("L2")){
            stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                    .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL2,vo->vo));
        }

        if(indexReq.getLevel().equals("L3")){
            stringStatisticsExcelVoMap = statisticsExcelVoList.stream()
                    .collect(Collectors.toMap(StatisticsExcelVo::getIndustryNameL3,vo->vo));
        }

        int i = 1;
        for(StatisticsExcelResp resp : statisticsExcelRespList){
            try{
                resp.setIdx(i++);

                Integer allCt = 0;
                Double allMv = 0.0;
                Double allAmt = 0.0;

                Double pctChg = 0.0;

                if(indexReq.getLevel().equals("market")){
                    allCt = statisticsExcelVoList.get(0).getAllCt();
                    allMv = statisticsExcelVoList.get(0).getAllMv();
                    allAmt = statisticsExcelVoList.get(0).getAllAmt();
                }
                if(indexReq.getLevel().equals("L1")){
                    allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllCt();
                    allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllMv();
                    allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL1()).getAllAmt();
                    pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL1()).getPctChange();
                }

                if(indexReq.getLevel().equals("L2")){
                    allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllCt();
                    allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllMv();
                    allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL2()).getAllAmt();
                    pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL2()).getPctChange();
                }

                if(indexReq.getLevel().equals("L3")){
                    allCt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllCt();
                    allMv = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllMv();
                    allAmt = stringStatisticsExcelVoMap.get(resp.getIndustryNameL3()).getAllAmt();
                    pctChg = stringShenWanDailyVoMap.get(resp.getIndustryNameL3()).getPctChange();
                }

                resp.setAllCt(allCt);
                resp.setAllMv(allMv);
                resp.setAllAmt(allAmt);
                resp.setPctChg(pctChg);

                resp.setMv(Double.parseDouble(String.format("%.2f",resp.getMv() / 10000)));
                resp.setAllMv(Double.parseDouble(String.format("%.2f",resp.getAllMv() / 10000)));
                resp.setAmt(Double.parseDouble(String.format("%.2f",resp.getAmt() / 100000)));
                resp.setAllAmt(Double.parseDouble(String.format("%.2f",resp.getAllAmt() / 100000)));

                resp.setCtPct(Double.parseDouble(String.format("%.2f",100*resp.getCt().doubleValue() / resp.getAllCt().doubleValue())));
                resp.setMvPct(Double.parseDouble(String.format("%.2f",100*resp.getMv() / resp.getAllMv())));
                resp.setAmtPct(Double.parseDouble(String.format("%.2f",100*resp.getAmt() / resp.getAllAmt())));

                //换手率
                resp.setTurnover(Double.parseDouble(String.format("%.2f",resp.getAmt() / resp.getMv())));
                resp.setAllTurnover(Double.parseDouble(String.format("%.2f",resp.getAllAmt() / resp.getAllMv())));

            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }

        }
        return statisticsExcelRespList;
    }

    @Override
    public List<StatisticsAllExcelResp> getStatisticsLevelAllExcel(IndexReq indexReq){
        System.out.println("----statistics_level_all_excel Servcie----");
        indexMapper.setSqlMode();
        List<StatisticsAllExcelResp> statisticsAllExcelRespList = indexMapper.selectStatisticsLevelAllExcel(indexReq);

        AtomicInteger index = new AtomicInteger(1);
        statisticsAllExcelRespList.stream().filter(resp-> resp.getTradeDate() != null).forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            try{
                resp.setAmt(Double.parseDouble(String.format("%.2f",resp.getAmt() / 100000)));
                resp.setMv(Double.parseDouble(String.format("%.2f",resp.getMv() / 10000)));
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getAsset() / 10000)));
                resp.setIncome(Double.parseDouble(String.format("%.2f",resp.getIncome() / 10000)));
                resp.setProfit(Double.parseDouble(String.format("%.2f",resp.getProfit() / 10000)));
                resp.setAvgMv(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getCt())));

                resp.setPe(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getProfit())));
                resp.setPb(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getAsset())));
                resp.setPs(Double.parseDouble(String.format("%.2f",resp.getMv() / resp.getIncome())));

                resp.setRoe(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getAsset())));
                resp.setProfitRate(Double.parseDouble(String.format("%.2f",resp.getProfit() / resp.getIncome())));
                resp.setTurnover(Double.parseDouble(String.format("%.2f",resp.getTurnover())));


            }catch (Exception e){
                System.out.println("捕获异常:" + e.getMessage());
            }
        });
        return statisticsAllExcelRespList;
    }

    @Override
    public List<DailyOverviewResp> getDailyPctOverview(IndexReq indexReq){
        System.out.println("----daily_overview service----");

        List<DailyOverviewResp> dailyOverviewRespList = null;
        if(indexReq.getSelectId() == 1){
            dailyOverviewRespList = indexMapper.selectDailyPctOverview(indexReq);
        }

        if(indexReq.getSelectId() == 2){
            dailyOverviewRespList = indexMapper.selectDailyPctOriginOverview(indexReq);
        }

        if(indexReq.getSelectId() == 3){
            dailyOverviewRespList = indexMapper.selectDailyPbOverview(indexReq);
        }

        AtomicInteger idx = new AtomicInteger(1);
        dailyOverviewRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());

            if(resp.getPctChange() != null){
                if(resp.getPctChange() != null){
                    resp.setPctChange(Double.parseDouble(String.format("%.2f",resp.getPctChange())));
                }

                if(resp.getDay1() != null){
                    resp.setDay1(Double.parseDouble(String.format("%.2f",resp.getDay1())));
                }

                if(resp.getDay2() != null){
                    resp.setDay2(Double.parseDouble(String.format("%.2f",resp.getDay2())));
                }

                if(resp.getDay3() != null){
                    resp.setDay3(Double.parseDouble(String.format("%.2f",resp.getDay3())));
                }

                if(resp.getDay4() != null){
                    resp.setDay4(Double.parseDouble(String.format("%.2f",resp.getDay4())));
                }

                if(resp.getDay5() != null){
                    resp.setDay5(Double.parseDouble(String.format("%.2f",resp.getDay5())));
                }

                if(resp.getDay6() != null){
                    resp.setDay6(Double.parseDouble(String.format("%.2f",resp.getDay6())));
                }

                if(resp.getDay7() != null){
                    resp.setDay7(Double.parseDouble(String.format("%.2f",resp.getDay7())));
                }

                if(resp.getDay8() != null){
                    resp.setDay8(Double.parseDouble(String.format("%.2f",resp.getDay8())));
                }

                if(resp.getDay9() != null){
                    resp.setDay9(Double.parseDouble(String.format("%.2f",resp.getDay9())));
                }

                if(resp.getDay10() != null){
                    resp.setDay10(Double.parseDouble(String.format("%.2f",resp.getDay10())));
                }

                if(resp.getDay11() != null){
                    resp.setDay11(Double.parseDouble(String.format("%.2f",resp.getDay11())));
                }

                if(resp.getDay12() != null){
                    resp.setDay12(Double.parseDouble(String.format("%.2f",resp.getDay12())));
                }

                if(resp.getDay13() != null){
                    resp.setDay13(Double.parseDouble(String.format("%.2f",resp.getDay13())));
                }

                if(resp.getDay14() != null){
                    resp.setDay14(Double.parseDouble(String.format("%.2f",resp.getDay14())));
                }

                if(resp.getDay15() != null){
                    resp.setDay15(Double.parseDouble(String.format("%.2f",resp.getDay15())));
                }

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }

        });
        return dailyOverviewRespList;
    }

    @Override
    public List<WeekOverviewResp> getWeekOverview(IndexReq indexReq){
        System.out.println("----week_overview service----");
        List<WeekOverviewResp> weekOverviewRespList = null;

        if(indexReq.getSelectId() == 1){
            weekOverviewRespList = indexMapper.selectWeekPctOverview(indexReq);
        }

        if(indexReq.getSelectId() == 2){
            weekOverviewRespList = indexMapper.selectWeekPctOriginOverview(indexReq);
        }

        if(indexReq.getSelectId() == 3){
            weekOverviewRespList = indexMapper.selectWeekPbOverview(indexReq);
        }
        AtomicInteger idx = new AtomicInteger(1);
        weekOverviewRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());

            if(resp.getPctChange() != null){
                if(resp.getPctChange() != null){
                    resp.setPctChange(Double.parseDouble(String.format("%.2f",resp.getPctChange())));
                }

                if(resp.getWeek1() != null){
                    resp.setWeek1(Double.parseDouble(String.format("%.2f",resp.getWeek1())));
                }

                if(resp.getWeek2() != null){
                    resp.setWeek2(Double.parseDouble(String.format("%.2f",resp.getWeek2())));
                }

                if(resp.getWeek3() != null){
                    resp.setWeek3(Double.parseDouble(String.format("%.2f",resp.getWeek3())));
                }

                if(resp.getWeek4() != null){
                    resp.setWeek4(Double.parseDouble(String.format("%.2f",resp.getWeek4())));
                }

                if(resp.getWeek5() != null){
                    resp.setWeek5(Double.parseDouble(String.format("%.2f",resp.getWeek5())));
                }

                if(resp.getWeek6() != null){
                    resp.setWeek6(Double.parseDouble(String.format("%.2f",resp.getWeek6())));
                }

                if(resp.getWeek7() != null){
                    resp.setWeek7(Double.parseDouble(String.format("%.2f",resp.getWeek7())));
                }

                if(resp.getWeek8() != null){
                    resp.setWeek8(Double.parseDouble(String.format("%.2f",resp.getWeek8())));
                }

                if(resp.getWeek9() != null){
                    resp.setWeek9(Double.parseDouble(String.format("%.2f",resp.getWeek9())));
                }

                if(resp.getWeek10() != null){
                    resp.setWeek10(Double.parseDouble(String.format("%.2f",resp.getWeek10())));
                }

                if(resp.getWeek11() != null){
                    resp.setWeek11(Double.parseDouble(String.format("%.2f",resp.getWeek11())));
                }

                if(resp.getWeek12() != null){
                    resp.setWeek12(Double.parseDouble(String.format("%.2f",resp.getWeek12())));
                }

                if(resp.getWeek13() != null){
                    resp.setWeek13(Double.parseDouble(String.format("%.2f",resp.getWeek13())));
                }

                if(resp.getWeek14() != null){
                    resp.setWeek14(Double.parseDouble(String.format("%.2f",resp.getWeek14())));
                }

                if(resp.getWeek15() != null){
                    resp.setWeek15(Double.parseDouble(String.format("%.2f",resp.getWeek15())));
                }

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }

        });
        return weekOverviewRespList;
    }

    @Override
    public List<MonthOverviewResp> getMonthOverview(IndexReq indexReq){
        System.out.println("----month_overview service----");

        List<MonthOverviewResp> monthOverviewRespList = null;
        if(indexReq.getSelectId() == 1){
            monthOverviewRespList = indexMapper.selectMonthPctOverview(indexReq);
        }

        if(indexReq.getSelectId() == 2){
            monthOverviewRespList = indexMapper.selectMonthPctOriginOverview(indexReq);
        }

        if(indexReq.getSelectId() == 3){
            monthOverviewRespList = indexMapper.selectMonthPbOverview(indexReq);
        }

        AtomicInteger idx = new AtomicInteger(1);
        monthOverviewRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());

            if(resp.getPctChange() != null){
                if(resp.getPctChange() != null){
                    resp.setPctChange(Double.parseDouble(String.format("%.2f",resp.getPctChange())));
                }

                if(resp.getMonth1() != null){
                    resp.setMonth1(Double.parseDouble(String.format("%.2f",resp.getMonth1())));
                }

                if(resp.getMonth2() != null){
                    resp.setMonth2(Double.parseDouble(String.format("%.2f",resp.getMonth2())));
                }

                if(resp.getMonth3() != null){
                    resp.setMonth3(Double.parseDouble(String.format("%.2f",resp.getMonth3())));
                }

                if(resp.getMonth4() != null){
                    resp.setMonth4(Double.parseDouble(String.format("%.2f",resp.getMonth4())));
                }

                if(resp.getMonth5() != null){
                    resp.setMonth5(Double.parseDouble(String.format("%.2f",resp.getMonth5())));
                }

                if(resp.getMonth6() != null){
                    resp.setMonth6(Double.parseDouble(String.format("%.2f",resp.getMonth6())));
                }

                if(resp.getMonth7() != null){
                    resp.setMonth7(Double.parseDouble(String.format("%.2f",resp.getMonth7())));
                }

                if(resp.getMonth8() != null){
                    resp.setMonth8(Double.parseDouble(String.format("%.2f",resp.getMonth8())));
                }

                if(resp.getMonth9() != null){
                    resp.setMonth9(Double.parseDouble(String.format("%.2f",resp.getMonth9())));
                }

                if(resp.getMonth10() != null){
                    resp.setMonth10(Double.parseDouble(String.format("%.2f",resp.getMonth10())));
                }

                if(resp.getMonth11() != null){
                    resp.setMonth11(Double.parseDouble(String.format("%.2f",resp.getMonth11())));
                }

                if(resp.getMonth12() != null){
                    resp.setMonth12(Double.parseDouble(String.format("%.2f",resp.getMonth12())));
                }

                if(resp.getMonth13() != null){
                    resp.setMonth13(Double.parseDouble(String.format("%.2f",resp.getMonth13())));
                }

                if(resp.getMonth14() != null){
                    resp.setMonth14(Double.parseDouble(String.format("%.2f",resp.getMonth14())));
                }

                if(resp.getMonth15() != null){
                    resp.setMonth15(Double.parseDouble(String.format("%.2f",resp.getMonth15())));
                }

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }

        });
        return monthOverviewRespList;
    }

    @Override
    public List<SeasonOverviewResp> getSeasonOverview(IndexReq indexReq){
        System.out.println("----season_overview service----");
        List<SeasonOverviewResp> seasonOverviewRespList = null;

        if(indexReq.getSelectId() == 1){
            seasonOverviewRespList = indexMapper.selectSeasonPctOverview(indexReq);
        }

        if(indexReq.getSelectId() == 2){
            seasonOverviewRespList = indexMapper.selectSeasonPctOriginOverview(indexReq);
        }

        if(indexReq.getSelectId() == 3){
            seasonOverviewRespList = indexMapper.selectSeasonPbOverview(indexReq);
        }

        AtomicInteger idx = new AtomicInteger(1);
        seasonOverviewRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());

            if(resp.getPctChange() != null){
                if(resp.getPctChange() != null){
                    resp.setPctChange(Double.parseDouble(String.format("%.2f",resp.getPctChange())));
                }

                if(resp.getSeason1() != null){
                    resp.setSeason1(Double.parseDouble(String.format("%.2f",resp.getSeason1())));
                }

                if(resp.getSeason2() != null){
                    resp.setSeason2(Double.parseDouble(String.format("%.2f",resp.getSeason2())));
                }

                if(resp.getSeason3() != null){
                    resp.setSeason3(Double.parseDouble(String.format("%.2f",resp.getSeason3())));
                }

                if(resp.getSeason4() != null){
                    resp.setSeason4(Double.parseDouble(String.format("%.2f",resp.getSeason4())));
                }

                if(resp.getSeason5() != null){
                    resp.setSeason5(Double.parseDouble(String.format("%.2f",resp.getSeason5())));
                }

                if(resp.getSeason6() != null){
                    resp.setSeason6(Double.parseDouble(String.format("%.2f",resp.getSeason6())));
                }

                if(resp.getSeason7() != null){
                    resp.setSeason7(Double.parseDouble(String.format("%.2f",resp.getSeason7())));
                }

                if(resp.getSeason8() != null){
                    resp.setSeason8(Double.parseDouble(String.format("%.2f",resp.getSeason8())));
                }

                if(resp.getSeason9() != null){
                    resp.setSeason9(Double.parseDouble(String.format("%.2f",resp.getSeason9())));
                }

                if(resp.getSeason10() != null){
                    resp.setSeason10(Double.parseDouble(String.format("%.2f",resp.getSeason10())));
                }

                if(resp.getSeason11() != null){
                    resp.setSeason11(Double.parseDouble(String.format("%.2f",resp.getSeason11())));
                }

                if(resp.getSeason12() != null){
                    resp.setSeason12(Double.parseDouble(String.format("%.2f",resp.getSeason12())));
                }

                if(resp.getSeason13() != null){
                    resp.setSeason13(Double.parseDouble(String.format("%.2f",resp.getSeason13())));
                }

                if(resp.getSeason14() != null){
                    resp.setSeason14(Double.parseDouble(String.format("%.2f",resp.getSeason14())));
                }

                if(resp.getSeason15() != null){
                    resp.setSeason15(Double.parseDouble(String.format("%.2f",resp.getSeason15())));
                }

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }

        });
        return seasonOverviewRespList;
    }

    @Override
    public List<YearOverviewResp> getYearOverview(IndexReq indexReq){
        System.out.println("----year_overview service----");
        List<YearOverviewResp> yearOverviewRespList = null;

        if(indexReq.getSelectId() == 1){
            yearOverviewRespList = indexMapper.selectYearPctOverview(indexReq);
        }

        if(indexReq.getSelectId() == 2){
            yearOverviewRespList = indexMapper.selectYearPctOriginOverview(indexReq);
        }

        if(indexReq.getSelectId() == 3){
            yearOverviewRespList = indexMapper.selectYearPbOverview(indexReq);
        }

        AtomicInteger idx = new AtomicInteger(1);
        yearOverviewRespList.stream().forEach(resp->{
            resp.setIdx(idx.getAndIncrement());

            if(resp.getPctChange() != null){
                if(resp.getPctChange() != null){
                    resp.setPctChange(Double.parseDouble(String.format("%.2f",resp.getPctChange())));
                }

                if(resp.getYear1() != null){
                    resp.setYear1(Double.parseDouble(String.format("%.2f",resp.getYear1())));
                }

                if(resp.getYear2() != null){
                    resp.setYear2(Double.parseDouble(String.format("%.2f",resp.getYear2())));
                }

                if(resp.getYear3() != null){
                    resp.setYear3(Double.parseDouble(String.format("%.2f",resp.getYear3())));
                }

                if(resp.getYear4() != null){
                    resp.setYear4(Double.parseDouble(String.format("%.2f",resp.getYear4())));
                }

                if(resp.getYear5() != null){
                    resp.setYear5(Double.parseDouble(String.format("%.2f",resp.getYear5())));
                }

                if(resp.getYear6() != null){
                    resp.setYear6(Double.parseDouble(String.format("%.2f",resp.getYear6())));
                }

                if(resp.getYear7() != null){
                    resp.setYear7(Double.parseDouble(String.format("%.2f",resp.getYear7())));
                }

                if(resp.getYear8() != null){
                    resp.setYear8(Double.parseDouble(String.format("%.2f",resp.getYear8())));
                }

                if(resp.getYear9() != null){
                    resp.setYear9(Double.parseDouble(String.format("%.2f",resp.getYear9())));
                }

                if(resp.getYear10() != null){
                    resp.setYear10(Double.parseDouble(String.format("%.2f",resp.getYear10())));
                }

                if(resp.getYear11() != null){
                    resp.setYear11(Double.parseDouble(String.format("%.2f",resp.getYear11())));
                }

                if(resp.getYear12() != null){
                    resp.setYear12(Double.parseDouble(String.format("%.2f",resp.getYear12())));
                }

                if(resp.getYear13() != null){
                    resp.setYear13(Double.parseDouble(String.format("%.2f",resp.getYear13())));
                }

                if(resp.getYear14() != null){
                    resp.setYear14(Double.parseDouble(String.format("%.2f",resp.getYear14())));
                }

                if(resp.getYear15() != null){
                    resp.setYear15(Double.parseDouble(String.format("%.2f",resp.getYear15())));
                }

                //计算市值
                resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
                //计算净资产
                if(resp.getPb() != null && resp.getPb() != 0){
                    resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
                }
            }

        });
        return yearOverviewRespList;
    }
}

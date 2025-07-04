package com.example.springboot.index.service.impl;

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
        return null;
    }

    @Override
    public List<FinaMain3Resp> getFinaMain3(ConceptMemberReq conceptMemberReq){
        System.out.println("----fina_main3 service----");
        return indexMapper.selectFinaMain3(conceptMemberReq);
    }

    @Override
    public List<CompanyInfoResp> getCompanyInfo(ConceptMemberReq conceptMemberReq){
        System.out.println("----company_info service----");
        return null;
    }
}

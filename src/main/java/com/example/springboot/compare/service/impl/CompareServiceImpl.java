package com.example.springboot.compare.service.impl;

import com.example.springboot.compare.mapper.CompareMapper;
import com.example.springboot.compare.service.CompareService;
import com.example.springboot.compare.vo.req.CompareReq;
import com.example.springboot.compare.vo.resp.DailyCompareResp;
import com.example.springboot.compare.vo.vo.DailyCompareVo;
import com.example.springboot.compare.vo.vo.DailyVo;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompareServiceImpl implements CompareService {
    @Autowired
    CompareMapper compareMapper;
    @Override
    public DailyCompareResp getDaily(CompareReq compareReq){
        System.out.println("----Compare Service----");

        //System.out.println(tmp[0]);
        compareReq.setSymbol1(compareReq.getTsCode1().split("\\.")[0]);
        compareReq.setSymbol2(compareReq.getTsCode2().split("\\.")[0]);

        List<DailyCompareVo> dailyCompareVoList = compareMapper.selectBenchmark(compareReq);
        List<DailyVo> dailyVoList1 = compareMapper.selectUpDaily(compareReq);
        List<DailyVo> dailyVoList2 = compareMapper.selectDownDaily(compareReq);

        Double openFirstDay1 = dailyVoList1.get(0).getOpen();
        Double openFirstDay2 = dailyVoList2.get(0).getOpen();

        //将对比的两条K线，先转成map
        Map<String,DailyVo> dailyVoMap1 = dailyVoList1.stream()
                .collect(Collectors.toMap(DailyVo::getTradeDate,vo->vo));

        Map<String,DailyVo> dailyVoMap2 = dailyVoList2.stream()
                .collect(Collectors.toMap(DailyVo::getTradeDate,vo->vo));

        //组装对比行情
        dailyCompareVoList.stream().forEach(compare->{
            String tradeDate = compare.getTradeDate();

            int coefficient1 = 1;
            int coefficient2 = 1;
            if(compareReq.getSelectId1() == 1){
                coefficient1 = 1000;
            }else{
                coefficient1 = 10;
            }

            if(compareReq.getSelectId2() == 1){
                coefficient2 = 1000;
            }else{
                coefficient2 = 10;
            }


            //对比1的行情数据
            DailyVo dailyVo1 = dailyVoMap1.get(tradeDate);
            if(dailyVo1 != null){
                compare.setOpen1(Double.parseDouble(String.format("%.2f",coefficient1*dailyVo1.getOpen()/openFirstDay1)));
                compare.setClose1(Double.parseDouble(String.format("%.2f",coefficient1*dailyVo1.getClose()/openFirstDay1)));
                compare.setLow1(Double.parseDouble(String.format("%.2f",coefficient1*dailyVo1.getLow()/openFirstDay1)));
                compare.setHigh1(Double.parseDouble(String.format("%.2f",coefficient1*dailyVo1.getHigh()/openFirstDay1)));
                compare.setPctChg1(Double.parseDouble(String.format("%.2f",dailyVo1.getPctChg())));
            }

            //对比2的行情数据
            DailyVo dailyVo2 = dailyVoMap2.get(tradeDate);
            if(dailyVo2 != null){
                compare.setOpen2(Double.parseDouble(String.format("%.2f",coefficient2*dailyVo2.getOpen()/openFirstDay2)));
                compare.setClose2(Double.parseDouble(String.format("%.2f",coefficient2*dailyVo2.getClose()/openFirstDay2)));
                compare.setLow2(Double.parseDouble(String.format("%.2f",coefficient2*dailyVo2.getLow()/openFirstDay2)));
                compare.setHigh2(Double.parseDouble(String.format("%.2f",coefficient2*dailyVo2.getHigh()/openFirstDay2)));
                compare.setPctChg2(Double.parseDouble(String.format("%.2f",dailyVo2.getPctChg())));
            }
        });

        //组装为对比行情出参
        DailyCompareResp dailyCompareResp = new DailyCompareResp();
        dailyCompareResp.setTsFirstName(compareReq.getName1());
        dailyCompareResp.setTsFirstCode(compareReq.getTsCode1());
        dailyCompareResp.setTsSecondName(compareReq.getName2());
        dailyCompareResp.setTsSecondCode(compareReq.getTsCode2());
        dailyCompareResp.setDailyCompareVoList(dailyCompareVoList);
        return dailyCompareResp;
    }
}

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
        List<DailyCompareVo> dailyCompareVoList = compareMapper.selectBenchmark(compareReq);
        List<DailyVo> dailyVoList1 = compareMapper.selectUpDaily(compareReq);
        List<DailyVo> dailyVoList2 = compareMapper.selectDownDaily(compareReq);

        //将对比的两条K线，先转成map
        Map<String,DailyVo> dailyVoMap1 = dailyVoList1.stream()
                .collect(Collectors.toMap(DailyVo::getTradeDate,vo->vo));

        Map<String,DailyVo> dailyVoMap2 = dailyVoList2.stream()
                .collect(Collectors.toMap(DailyVo::getTradeDate,vo->vo));

        //组装对比行情
        dailyCompareVoList.stream().forEach(compare->{
            String tradeDate = compare.getTradeDate();

            //对比1的行情数据
            DailyVo dailyVo1 = dailyVoMap1.get(tradeDate);
            if(dailyVo1 != null){
                compare.setOpen1(dailyVo1.getOpen());
                compare.setClose1(dailyVo1.getClose());
                compare.setLow1(dailyVo1.getLow());
                compare.setHigh1(dailyVo1.getHigh());
                compare.setAmount1(dailyVo1.getAmount());
                compare.setTurnOverRate1(dailyVo1.getTurnOverRate());
                compare.setTurnOverRateF1(dailyVo1.getTurnOverRateF());
                compare.setPctChg1(dailyVo1.getPctChg());
                compare.setPb1(dailyVo1.getPb());
                compare.setPe1(dailyVo1.getPe());
                compare.setTotalMv1(dailyVo1.getTotalMv());
            }

            //对比2的行情数据
            DailyVo dailyVo2 = dailyVoMap2.get(tradeDate);
            if(dailyVo2 != null){
                compare.setOpen2(dailyVo2.getOpen());
                compare.setClose2(dailyVo2.getClose());
                compare.setLow2(dailyVo2.getLow());
                compare.setHigh2(dailyVo2.getHigh());
                compare.setAmount2(dailyVo2.getAmount());
                compare.setTurnOverRate2(dailyVo2.getTurnOverRate());
                compare.setTurnOverRateF2(dailyVo2.getTurnOverRateF());
                compare.setPctChg2(dailyVo2.getPctChg());
                compare.setPb2(dailyVo2.getPb());
                compare.setPe2(dailyVo2.getPe());
                compare.setTotalMv2(dailyVo2.getTotalMv());
            }
        });

        //组装为对比行情出参
        DailyCompareResp dailyCompareResp = new DailyCompareResp();
        dailyCompareResp.setTsFirstName("平安银行");
        dailyCompareResp.setTsFirstCode("000001.SZ");
        dailyCompareResp.setTsSecondName("万科");
        dailyCompareResp.setTsSecondCode("000002.SZ");
        dailyCompareResp.setDailyCompareVoList(dailyCompareVoList);
        return dailyCompareResp;
    }
}

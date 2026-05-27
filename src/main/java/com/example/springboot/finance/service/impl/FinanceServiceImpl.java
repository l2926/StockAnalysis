package com.example.springboot.finance.service.impl;

import com.example.springboot.finance.mapper.FinanceMapper;
import com.example.springboot.finance.service.FinanceService;
import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceHistoryResp;
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
    }

    @Override
    public List<FinanceHistoryResp> getFinanceHistory(FinanceReq financeReq){
        System.out.println("----finance history service----");
        System.out.println(financeReq.getSelectId());

        List<FinanceHistoryResp> financeHistoryRespList = null;
        if(financeReq.getSelectId() == 1){
            financeHistoryRespList = financeMapper.selectTotalAssetsHistory(financeReq);
        }

        if(financeReq.getSelectId() == 2){
            financeHistoryRespList = financeMapper.selectTotalLiabHistory(financeReq);
        }

        if(financeReq.getSelectId() == 3){
            financeHistoryRespList = financeMapper.selectTotalRevenueHistory(financeReq);
        }

        if(financeReq.getSelectId() == 4){
            financeHistoryRespList = financeMapper.selectProfitHistory(financeReq);
        }

        if(financeReq.getSelectId() == 5){
            financeHistoryRespList = financeMapper.selectNetAssetsHistory(financeReq);
        }

        if(financeReq.getSelectId() == 6){
            financeHistoryRespList = financeMapper.selectLeverHistory(financeReq);
        }

        if(financeReq.getSelectId() == 7){
            financeHistoryRespList = financeMapper.selectProfitRateHistory(financeReq);
        }

        if(financeReq.getSelectId() == 8){
            financeHistoryRespList = financeMapper.selectROEHistory(financeReq);
        }

        if(financeReq.getSelectId() == 9){
            financeHistoryRespList = financeMapper.selectROAHistory(financeReq);
        }

        AtomicInteger index = new AtomicInteger(1);
        financeHistoryRespList.stream().forEach(resp->{
            resp.setIdx(index.getAndIncrement());
            resp.setTotalMv(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / 10000)));
            //计算净资产
            if(resp.getPb() != 0){
                resp.setAsset(Double.parseDouble(String.format("%.2f",resp.getTotalMv() / resp.getPb())));
            }

            Integer selectID = financeReq.getSelectId();
            if (selectID == 1 || selectID == 2 || selectID == 3 || selectID == 4 || selectID == 5){
                try{
                    resp.setNum2010(Double.parseDouble(String.format("%.2f",resp.getNum2010() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2011(Double.parseDouble(String.format("%.2f",resp.getNum2011() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2012(Double.parseDouble(String.format("%.2f",resp.getNum2012() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2013(Double.parseDouble(String.format("%.2f",resp.getNum2013() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2014(Double.parseDouble(String.format("%.2f",resp.getNum2014() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2015(Double.parseDouble(String.format("%.2f",resp.getNum2015() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2016(Double.parseDouble(String.format("%.2f",resp.getNum2016() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2017(Double.parseDouble(String.format("%.2f",resp.getNum2017() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2018(Double.parseDouble(String.format("%.2f",resp.getNum2018() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2019(Double.parseDouble(String.format("%.2f",resp.getNum2019() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2020(Double.parseDouble(String.format("%.2f",resp.getNum2020() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2021(Double.parseDouble(String.format("%.2f",resp.getNum2021() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2022(Double.parseDouble(String.format("%.2f",resp.getNum2022() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2023(Double.parseDouble(String.format("%.2f",resp.getNum2023() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2024(Double.parseDouble(String.format("%.2f",resp.getNum2024() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2025(Double.parseDouble(String.format("%.2f",resp.getNum2025() / 100000000)));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
            }else{
                try{
                    resp.setNum2010(Double.parseDouble(String.format("%.2f",resp.getNum2010())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2011(Double.parseDouble(String.format("%.2f",resp.getNum2011())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2012(Double.parseDouble(String.format("%.2f",resp.getNum2012())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2013(Double.parseDouble(String.format("%.2f",resp.getNum2013())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2014(Double.parseDouble(String.format("%.2f",resp.getNum2014())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2015(Double.parseDouble(String.format("%.2f",resp.getNum2015())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2016(Double.parseDouble(String.format("%.2f",resp.getNum2016())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2017(Double.parseDouble(String.format("%.2f",resp.getNum2017())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2018(Double.parseDouble(String.format("%.2f",resp.getNum2018())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2019(Double.parseDouble(String.format("%.2f",resp.getNum2019())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2020(Double.parseDouble(String.format("%.2f",resp.getNum2020())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2021(Double.parseDouble(String.format("%.2f",resp.getNum2021())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2022(Double.parseDouble(String.format("%.2f",resp.getNum2022())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2023(Double.parseDouble(String.format("%.2f",resp.getNum2023())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2024(Double.parseDouble(String.format("%.2f",resp.getNum2024())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
                try{
                    resp.setNum2025(Double.parseDouble(String.format("%.2f",resp.getNum2025())));
                }catch (Exception e){
                    System.out.println("年数据异常"+e);
                }
            }
        });

        return financeHistoryRespList;
    }
}

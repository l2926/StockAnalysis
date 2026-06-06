package com.example.springboot.finance.mapper;

import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceHistoryResp;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;
import com.example.springboot.finance.vo.resp.HsgtHoldResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @desc 财务 mapper
 * @author lizijian
 * @date 2025-06-08
 */

@Mapper
public interface FinanceMapper {
    Integer selectCount();
    List<FinanceOverviewResp> getFinanceOverview(FinanceReq financeReq);
    List<FinanceHistoryResp> selectNetAssetsHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectTotalAssetsHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectTotalLiabHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectLeverHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectTotalRevenueHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectProfitHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectProfitRateHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectROEHistory(FinanceReq financeReq);
    List<FinanceHistoryResp> selectROAHistory(FinanceReq financeReq);
    List<HsgtHoldResp> selectHsgtHoldHistory(FinanceReq financeReq);
}

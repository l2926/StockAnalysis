package com.example.springboot.finance.service;

import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceHistoryResp;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;
import com.example.springboot.finance.vo.resp.HsgtHoldResp;

import java.util.List;

/**
 * @desc 财务 服务层
 * @author lizijian
 * @date 2025-06-08
 */

public interface FinanceService {
    /**
     * 获取财务概览数据
     */
    List<FinanceOverviewResp> getFinanceOverview(FinanceReq financeReq);
    /**
     * 获取历史财务数据
     */
    List<FinanceHistoryResp> getFinanceHistory(FinanceReq financeReq);
    /**
     * 获取沪深股通历史持股
     */
    List<HsgtHoldResp> getHsgtHold(FinanceReq financeReq);
}

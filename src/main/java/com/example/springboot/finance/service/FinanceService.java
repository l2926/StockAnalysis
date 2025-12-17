package com.example.springboot.finance.service;

import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;

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
}

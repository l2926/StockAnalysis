package com.example.springboot.finance.mapper;

import com.example.springboot.finance.vo.req.FinanceReq;
import com.example.springboot.finance.vo.resp.FinanceOverviewResp;
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
}

package com.example.springboot.market.vo.resp;

import com.example.springboot.market.vo.vo.FinanceCenterVo;
import lombok.Data;

import java.util.List;

/**
 * @author lizijian
 * @desc 获取财务概览数据出参
 * @date 2025-06-11
 */

@Data
public class FinanceCenterResp {
    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 股票名称
     */
    private String tsName;
    /**
     * 财务数据vo
     */
    private List<FinanceCenterVo> financeCenterVoList;
}

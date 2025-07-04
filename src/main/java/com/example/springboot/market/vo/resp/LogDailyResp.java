package com.example.springboot.market.vo.resp;

import com.example.springboot.market.vo.vo.DailyVo;
import com.example.springboot.market.vo.vo.LogDailyVo;
import lombok.Data;

import java.util.List;

/**
 * @author lizijian
 * @desc 获取价格出参
 * @date 2025-06-22
 */

@Data
public class LogDailyResp {
    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 股票名称
     */
    private String tsName;
    /**
     * 股票日线VO
     */
    private List<LogDailyVo> logDailyVoList;
}

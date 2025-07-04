package com.example.springboot.compare.vo.resp;

import com.example.springboot.compare.vo.vo.DailyCompareVo;
import lombok.Data;

import java.util.List;

/**
 * @author lizijian
 * @desc 获取对比出参
 * @date 2025-05-29
 */

@Data
public class DailyCompareResp {
    /**
     * 第一个指数/股票代码
     */
    private String tsFirstCode;
    /**
     * 第一个指数/股票名称
     */
    private String tsFirstName;
    /**
     * 第二个指数/股票代码
     */
    private String tsSecondCode;
    /**
     * 第二个指数/股票名称
     */
    private String tsSecondName;
    /**
     * 两个指数或股票的日线vo
     */
    private List<DailyCompareVo> dailyCompareVoList;
}

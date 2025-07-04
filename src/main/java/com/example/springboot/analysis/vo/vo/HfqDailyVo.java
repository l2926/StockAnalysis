package com.example.springboot.analysis.vo.vo;

import lombok.Data;

/**
 * @desc 后复权出参
 * @author lizijian
 * @date 2026-06-16
 */

@Data
public class HfqDailyVo {
    private String tsCode;
    private Double close;
    private Double totalMv;
    private Double pb;
    private String tradeDate;
}

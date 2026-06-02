package com.example.springboot.index.vo.vo;

import lombok.Data;

/**
 * 申万每天涨跌幅
 */

@Data
public class ShenWanDailyVo {
    private String name;
    private Double pctChange;
}

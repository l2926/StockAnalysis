package com.example.springboot.index.vo.resp;

/**
 * 指数日概览 出参
 */

import lombok.Data;

@Data
public class DailyOverviewResp {
    private Integer idx;
    private String indexCode;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double day15;
    private Double day14;
    private Double day13;
    private Double day12;
    private Double day11;
    private Double day10;
    private Double day9;
    private Double day8;
    private Double day7;
    private Double day6;
    private Double day5;
    private Double day4;
    private Double day3;
    private Double day2;
    private Double day1;
    private Double pctChange;
    private Double pb;
    private Double totalMv;
    private String tradeDate;
}

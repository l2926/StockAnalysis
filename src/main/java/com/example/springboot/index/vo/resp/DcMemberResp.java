package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * @desc 东方财富板块成分 出参
 * @author lizijian
 * @date 2025-06025
 */

@Data
public class DcMemberResp {
    private Integer idx;
    private String conceptName; //概念名称
    private String conCode; //成员代码
    private String name; //成员名称
    private String tradeDate;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String market;
    private Double pctChg;
    private Double pb;
    private Double ps;
    private Double pe;
    private Double amount;
    private Double turnoverRate;
    private Double totalMv;
}

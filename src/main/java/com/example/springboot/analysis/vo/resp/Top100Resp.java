package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc A股百强名单 出参
 * @author lizijian
 * @date 2025-06-12
 */

@Data
public class Top100Resp {
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pct_chg;
    private Double close;
    private Double pe;
    private Double pb;
    private Double ps;
    private Double turnoverRate;
    private Double amount;
    private String market;
    private Double totalMv;
    private Double assets;
    private String tradeDate;
}

package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * 东方财富成员
 */

@Data
public class DcMember2Resp {
    private String tsCode;
    private String tradeDate;
    private String name;
    private String themeCode;
    private String reason;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private String market;
    private Double asset;
    private Double totalMv;
}

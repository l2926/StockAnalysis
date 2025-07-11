package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc 五日内两板以上
 * @author lizijian
 * @date 2025-06-13
 */

@Data
public class FiveDaysLimitResp {
    private Integer idx;
    private String tsCode;
    private String conceptName;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double close;
    private Integer up10Count5;
    private Integer up10Count10;
    private Integer up10Count15;
    private Integer up10Count20;
    private Integer down10Count5;
    private Integer down10Count10;
    private Integer down10Count15;
    private Integer down10Count20;
    private Integer contiUp;
    private String market;
    private Double totalMv;
    private Double pb;
    private Double assets;
    private String tradeDate;
}

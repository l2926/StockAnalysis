package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * 主营业务出参
 */


@Data
public class FinaMianResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private Double pctChg;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String mainBusiness;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}

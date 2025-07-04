package com.example.springboot.industry.vo.resp;

import lombok.Data;


/**
 * 十大股东出参
 */

@Data

public class TopHoldResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private Double pctChg;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String topHolder;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}

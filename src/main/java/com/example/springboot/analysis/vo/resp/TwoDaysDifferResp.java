package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc 两日行情差 出参
 * @author lizijian
 * @date 2025-06-03
 */

@Data
public class TwoDaysDifferResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChgTwoDays;
    private Double close1;
    private Double close2;
    private String market;
    private Double pb1;
    private Double pb2;
    private Double asset1;
    private Double asset2;
    private Double totalMv1;
    private Double totalMv2;
    private String tradeDate;
    private String startDate;
}

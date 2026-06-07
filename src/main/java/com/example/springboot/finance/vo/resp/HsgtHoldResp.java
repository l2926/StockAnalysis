package com.example.springboot.finance.vo.resp;

import lombok.Data;

/**
 * 沪深股通持股
 */

@Data
public class HsgtHoldResp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double num20260331;
    private Double num20251231;
    private Double num20250630;
    private Double num20241231;
    private Double num20230630;
    private Double num20221231;
    private Double num20220630;
    private Double num20211231;
    private Double num20210630;
    private Double num20201231;
    private Double num20200630;
    private Double num20191231;
    private Double num20181231;
    private Double num20180630;
    private Double num20170630;
    private Double pb;
    private String market;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}

package com.example.springboot.index.vo.resp;

import lombok.Data;

import java.util.Iterator;

/**
 * @desc 开盘啦 概念成员
 * @author lizijian
 * @date 2025-06-24
 */

@Data
public class KplConceptConsResp {
    private Integer idx;
    private String tsCode;
    private String conceptName;
    private String name;
    private String desc;
    private Integer hotNum;//人气值
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private Double pctChg;
    private Double close;
    private Double pb;
    private String market;
    private Double totalMv;
    private Double assets;
    private String tradeDate;
}

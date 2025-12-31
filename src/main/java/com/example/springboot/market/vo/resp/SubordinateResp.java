package com.example.springboot.market.vo.resp;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @autor: lizijian
 * @desc: 所属行业出参
 * @daet 2024-09-29
 */

@Data
public class SubordinateResp {
    private Integer idx;
    private String tsCode;
    private String name;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String indexCodeL1;
    private String indexCodeL2;
    private String indexCodeL3;
    private String concept;
}

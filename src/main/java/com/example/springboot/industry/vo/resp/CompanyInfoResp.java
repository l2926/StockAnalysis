package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * @desc 公司信息出参
 * @author lizijian
 * @date 2025-06-06
 */

@Data
public class CompanyInfoResp {
    private Integer idx;
    private String name;
    private String tradeDate;
    private String tsCode;
    private String comName;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String listDate;
    private String actName;
    private String actEntType;
    private String exchange;
    private String regCapital;
    private String setupDate;
    private String province;
    private String city;
    private String website;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
}

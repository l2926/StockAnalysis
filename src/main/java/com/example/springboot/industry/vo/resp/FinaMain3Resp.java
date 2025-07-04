package com.example.springboot.industry.vo.resp;

import lombok.Data;

/**
 * @desc 主营业务出参
 * @author lizijian
 * @date 2025-06-06
 */

@Data
public class FinaMain3Resp {
    private Integer idx;
    private String conceptName;
    private String name;
    private String tsCode;
    private String province;
    private String city;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String mainBusiness;
    private String businessScope;
    private String market;
    private Double pb;
    private Double asset;
    private Double totalMv;
    private String tradeDate;
}

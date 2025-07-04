package com.example.springboot.analysis.vo.resp;

import lombok.Data;

/**
 * @desc 涨停出参
 * @author lizijian
 * @date 2025-06-24
 */

@Data
public class LimitBoardResp {
    private Integer idx;
    private String name;
    private String tsCode;
    private String area;
    private String industryNameL1;
    private String industryNameL2;
    private String industryNameL3;
    private String luTime;
    private String lastTime;
    private String luDesc;
    private String theme;
    private String status;
    private String market;
    private Double asset;
    private Double totalMv;
    private Double pb;
    private String tradeDate;
}

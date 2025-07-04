package com.example.springboot.industry.vo.req;

import lombok.Data;

/**
 * @author lizijian
 * @desc 行业 入参
 * @date 2024-09-27
 */

@Data
public class IndustryReq {
    private Integer selectId;
    private Integer paraId;
    private Integer sortId;
    private Integer blockId;
    private String level;
    private String industryCode;
    private String tradeDate;
    private String startDate;
}

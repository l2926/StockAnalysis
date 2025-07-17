package com.example.springboot.index.vo.req;

import lombok.Data;

/**
 * @author lizijian
 * @desc 统计 入参
 */

@Data
public class StatisticsReq {
    private String level;
    private Integer blockId;
    private Integer mvId;
    private Integer paraId;
    private String tradeDate;
}

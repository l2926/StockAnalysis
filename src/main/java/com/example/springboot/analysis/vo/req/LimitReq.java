package com.example.springboot.analysis.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lizijian
 * @desc 涨停 入参
 * @date 2024-09-26
 */
@Data
public class LimitReq {
//    @JsonProperty("select_id")
    private Integer selectId;
//    @JsonProperty("sortId")
    private Integer sortId;
//    @JsonProperty("block_id")
    private Integer blockId;
//    @JsonProperty("mv_id")
    private Integer paraId;
//    @JsonProperty("trade_date")
    private String tradeDate;
    private String startDate;
}

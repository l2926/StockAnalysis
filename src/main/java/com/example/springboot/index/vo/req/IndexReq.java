package com.example.springboot.index.vo.req;

import lombok.Data;

/**
 * @author lizijian
 * @desc 指数 入参
 */

@Data
public class IndexReq {
    private String indexName;
    private Integer selectId;
    private String level;
    private Integer paraId;
    private Integer blockId;
    private String tradeDate;
    private String indexCode;
    private String symbol;
}

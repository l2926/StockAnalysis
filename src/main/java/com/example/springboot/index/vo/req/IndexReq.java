package com.example.springboot.index.vo.req;

import lombok.Data;

/**
 * @author lizijian
 * @desc 指数 入参
 */

@Data
public class IndexReq {
    private String indexName;
    private Integer paraId;
    private String tradeDate;
    private String indexCode;
    private String symbol;
}

package com.example.springboot.search.vo.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizijian
 * @desc 股票搜索入参
 * @date 2024-06-11
 */

@Data
public class QueryStockReq {
    private String queryParam;
}

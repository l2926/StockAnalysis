package com.example.springboot.search.vo.resp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lizijian
 * @desc 股票搜索出参
 * @date 2024-06-11
 */

@Data
public class QueryStockResp {
    /**
     * 股票代码
     */
    String tsCode;
    /**
     * 股票名称
     */
    String name;
}

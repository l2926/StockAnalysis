package com.example.springboot.compare.vo.req;

import lombok.Data;

/**
 * @desc 对比行情入参
 * @author lizijian
 * @date 2025-06-29
 */

@Data
public class CompareReq {
    private String tsCode1;
    private String symbol1;
    private String name1;
    private String tsCode2;
    private String symbol2;
    private String name2;
    private String tradeDate;
    private String startDate;
}

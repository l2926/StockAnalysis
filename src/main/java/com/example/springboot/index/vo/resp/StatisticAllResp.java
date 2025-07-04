package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * @author lizijian
 * @desc 获取行业统计出参
 */

@Data
public class StatisticAllResp {
    /**
     * 股票编码
     */
    private String indexCode;
    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 股票在该行业数量
     */
    private Integer count;
    /**
     * 股票在该行业总市值
     */
    private Double totalMv;
    /**
     * 自己计算的行业总市值
     */
    private Double totalMv2;
    /**
     * 市净率PB
     */
    private Double pb;
}

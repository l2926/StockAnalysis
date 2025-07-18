package com.example.springboot.index.vo.resp;

import lombok.Data;

/**
 * @desc 统计上面的参数 出参
 * @date 2025-07-17
 * @author lizijian
 */

@Data
public class StatisticsCountResp {
    private Integer upCount;
    private Integer downCount;
    private Double shPct;
    private Double szPct;
    private Double smallPct;
    private Double startupPct;
    private Double bjPct;
    private Double kcbPct;
}

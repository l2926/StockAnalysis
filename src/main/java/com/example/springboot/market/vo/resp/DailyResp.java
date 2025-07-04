package com.example.springboot.market.vo.resp;

import com.example.springboot.market.vo.vo.DailyVo;
import lombok.Data;

import java.util.List;

/**
 * @author lizijian
 * @desc 获取价格出参
 * @date 2024-09-19
 */

@Data
public class DailyResp {
    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 股票名称
     */
    private String tsName;
    /**
     * 股票日线VO
     */
    private List<DailyVo> dailyVoList;
}

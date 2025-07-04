package com.example.springboot.index.vo.resp;

import com.example.springboot.index.vo.vo.DailyVo;
import lombok.Data;

import java.util.List;

/**
 * @author lizijian
 * @desc 获取价格出参
 * @date 2025-03-09
 */

@Data
public class DailyResp {
    /**
     * 指数代码
     */
    private String indexCode;
    /**
     * 指数名称
     */
    private String indexName;
    /**
     * 指数日线vo
     */
    private List<DailyVo> dailyVoList;
}

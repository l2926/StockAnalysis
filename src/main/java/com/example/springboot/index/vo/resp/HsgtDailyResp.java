package com.example.springboot.index.vo.resp;

import com.example.springboot.index.vo.vo.HsgtDailyVo;
import lombok.Data;

import java.util.List;

/**
 * 北向资金 出参
 */

@Data
public class HsgtDailyResp {
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
    private List<HsgtDailyVo> hsgtDailyVoList;
}

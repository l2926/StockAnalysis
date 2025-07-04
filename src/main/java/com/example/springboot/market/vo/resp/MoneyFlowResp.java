package com.example.springboot.market.vo.resp;

import com.example.springboot.market.vo.vo.MoneyFlowVo;
import lombok.Data;

/**
 * @desc 资金流向出参
 * @author lizijian
 * @date 2025-06-28
 */

@Data
public class MoneyFlowResp {
    private String tsCode;
    private String tsName;
    private String tradeDate;
    private MoneyFlowVo moneyFlowVo;
}

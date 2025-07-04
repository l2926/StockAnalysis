package com.example.springboot.market.vo.resp;

import com.example.springboot.market.vo.vo.AssetCenterVo;
import lombok.Data;

import java.util.List;

/**
 * @desc 资产中心出参
 * @author  lizijian
 * @date 2025-06-12
 */

@Data
public class AssetCenterResp {
    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 股票名称
     */
    private String tsName;
    /**
     * 资产中心 vo层
     */
    private List<AssetCenterVo> assetCenterVoList;
}

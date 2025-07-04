package com.example.springboot.compare.service;

import com.example.springboot.compare.vo.req.CompareReq;
import com.example.springboot.compare.vo.resp.DailyCompareResp;

/**
 * @author lizijian
 * @desc 对比行情 服务层
 * @date 2025-05-29
 */

public interface CompareService {
    /**
     * 获取对比行情
     */
    DailyCompareResp getDaily(CompareReq compareReq);
}

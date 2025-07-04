package com.example.springboot.search.service;

import com.example.springboot.search.vo.resp.QueryStockResp;

/**
 * @desc 股票搜索控制器
 * @date 2024-06-11
 */
public interface SearchService {
    /**
     * @desc 股票搜索控制器
     * @param queryName
     * @return
     */
    QueryStockResp searchStock(String queryName);
}

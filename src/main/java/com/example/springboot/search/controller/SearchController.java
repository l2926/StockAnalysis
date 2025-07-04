package com.example.springboot.search.controller;

import com.example.springboot.search.vo.resp.QueryStockResp;
import com.example.springboot.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc 搜索股票控制器
 * @date 2024-06-11
 */

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;


    @GetMapping("/stock")
    public QueryStockResp searchStock(@RequestParam String queryName){
        return searchService.searchStock(queryName);
    }
}

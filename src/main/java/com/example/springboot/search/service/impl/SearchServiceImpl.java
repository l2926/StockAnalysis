package com.example.springboot.search.service.impl;

import com.example.springboot.search.mapper.SearchMapper;
import com.example.springboot.search.vo.resp.QueryStockResp;
import com.example.springboot.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;


    @Override
    public QueryStockResp searchStock(String queryName){
        System.out.println("======="+queryName+"======");

        QueryStockResp queryStockResp =  searchMapper.queryStock(queryName);
        return queryStockResp;
    }
}

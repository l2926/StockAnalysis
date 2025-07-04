package com.example.springboot.search.mapper;

import com.example.springboot.search.vo.resp.QueryStockResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lizijian
 * @desc 搜索mapper
 * @date 2024-06-11
 */
@Mapper
public interface SearchMapper {
    QueryStockResp queryStock(@Param("queryName") String queryName);
}

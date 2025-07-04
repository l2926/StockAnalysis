package com.example.springboot.compare.mapper;

import com.example.springboot.compare.vo.req.CompareReq;
import com.example.springboot.compare.vo.vo.DailyCompareVo;
import com.example.springboot.compare.vo.vo.DailyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lizijian
 * @desc 查询对比行情
 * @date 2025-05-29
 */

@Mapper
public interface CompareMapper {
    Integer selectCount();
    List<DailyCompareVo> selectBenchmark(CompareReq compareReq);
    List<DailyVo> selectUpDaily(CompareReq compareReq);
    List<DailyVo> selectDownDaily(CompareReq compareReq);
}

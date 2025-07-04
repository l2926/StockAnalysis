package com.example.springboot.compare.controller;

import com.example.springboot.compare.service.CompareService;
import com.example.springboot.compare.vo.req.CompareReq;
import com.example.springboot.compare.vo.resp.DailyCompareResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizijian
 * @desc 对比 控制类
 * @date 2025-05029
 */

@RestController
@RequestMapping("/compare")
public class CompareController {
    @Autowired
    CompareService compareService;

    @PostMapping("/daily")
    public DailyCompareResp getDaily(@RequestBody CompareReq compareReq){
        System.out.println("----Compare controller----");
        return compareService.getDaily(compareReq);
    }
}

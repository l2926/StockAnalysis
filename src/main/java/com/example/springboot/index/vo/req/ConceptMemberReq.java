package com.example.springboot.index.vo.req;

import lombok.Data;

/**
 * @desc 概念成员 入参
 * @author lizijian
 * @date 2025-06-26
 */

@Data
public class ConceptMemberReq {
    private Integer selectId;
    private String name;
    private String tradeDate;
}

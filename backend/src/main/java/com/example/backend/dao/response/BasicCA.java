package com.example.backend.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicCA {

    private String sid; // 学生学号

    private String type; // 综测类别

    private String rule_type;   // 综测大类

    private String rule_detail; // 综测细则

    private String url; // 材料链接

    private String check_status; // 审核状态 0表示未审核, 1表示已经审核

    private String check_msg; // 审核意见

}

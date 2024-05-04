package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // 编号

    @Column(name = "url")
    private String url; // 材料链接

    @Column(name = "check_status")
    private String check_status; // 审核状态 0表示未审核, 1表示已经审核

    @Column(name = "check_msg")
    private String check_msg; // 审核意见

    @Column(name = "submit_time")
    private Date submit_time; // 提交时间

    @Column(name = "academic_year")
    private Integer academic_year; //所属学年

    @Column(name = "sid")
    private String sid; // 学生学号

    @Column(name = "type")
    private String type; // 成果类别 (学科竞赛/艺术比赛/体育比赛/其他)

    @Column(name = "time")
    private Date time; // 获得时间

    @Column(name = "competition_name")
    private String competition_name; // 比赛名称

    @Column(name = "award_name")
    private String award_name; // 获奖名称

    @Column(name = "result_level")
    private String result_level; // 成果等级 (特等奖、一等奖、二等奖、三等奖...)

    @Column(name = "result_type")
    private String result_type; // 成果级别 (国际级/国家级/省部级/校级)

    @Column(name = "organization")
    private String organization; // 评奖组织单位

    @Column(name = "teammate")
    private String teammate; // 其他参与人员

    @Column(name = "rule_type")
    private String rule_type;   // 综测大类

    @Column(name = "rule_detail")
    private String rule_detail; // 综测细则

    @Column(name = "rule_score")
    private Float rule_score;   // 分数

    @Column(name = "check_score")
    private Float check_score;      // 审核分数

    @Column(name = "rule_accept")
    private Integer rule_accept;    // 是否接受

    @Column(name = "captain")
    private String captain;     // 负责人


}


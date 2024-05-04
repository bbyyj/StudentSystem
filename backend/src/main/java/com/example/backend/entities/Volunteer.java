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
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sid")
    private String sid; // 学生学号

    @Column(name = "organization")
    private String organization; // 志愿组织团体（全称）

    @Column(name = "name")
    private String name; // 志愿活动名称（全称）

    @Column(name = "type")
    private String type; // 志愿活动类型

    @Column(name = "begin")
    private Date begin; // 参与志愿活动开始时间

    @Column(name = "end")
    private Date end; // 参与志愿活动结束时间

    @Column(name = "time")
    private float time; // 志愿服务时长（X小时，有0.5小时）

    @Column(name = "url")
    private String url; // 材料链接

    @Column(name = "check_status")
    private String check_status; // 审核状态

    @Column(name = "check_msg")
    private String check_msg; // 审核意见

    // 添加新字段
    @Column(name = "rule_type")
    private String rule_type; // 综测大类

    @Column(name = "rule_detail")
    private String rule_detail; // 细则

    @Column(name = "rule_score")
    private float rule_score; // 细则分数

    @Column(name = "check_score")
    private float check_score; // 审核分数

    @Column(name = "rule_accept")
    private Integer rule_accept; // 是否加入综测，默认为0, not null

}

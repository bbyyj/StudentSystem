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
    private int id;

    @Column(name = "url")
    private String url; // 材料链接

    @Column(name = "check_status")
    private String check_status; // 审核状态

    @Column(name = "check_msg")
    private String check_msg; // 审核意见

    @Column(name = "sid")
    private String sid; // 学生学号

    @Column(name = "type")
    private String type; // 成果类别

    @Column(name = "time")
    private Date time; // 获得时间

    @Column(name = "competition_name")
    private String competition_name; // 比赛名称

    @Column(name = "award_name")
    private String award_name; // 获奖名称

    @Column(name = "result_level")
    private String result_level; // 成果等级

    @Column(name = "result_type")
    private String result_type; // 成果级别

    @Column(name = "organization")
    private String organization; // 评奖组织单位

    @Column(name = "teammate")
    private String teammate; // 其他参与人员


}


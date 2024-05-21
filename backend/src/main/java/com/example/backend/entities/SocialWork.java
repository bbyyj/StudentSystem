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
@Table(name = "social_work")
public class SocialWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sid")
    private String sid; // 学生学号

    @Column(name = "url")
    private String url; // 材料链接

    @Column(name = "check_status")
    private String check_status; // 审核状态 未审核/审核通过/审核不通过

    @Column(name = "check_msg")
    private String check_msg; // 审核意见

    @Column(name = "attend_time")
    private Date attend_time; // 参与时间

    @Column(name = "commendation")
    private String commendation; // 职位/表彰

    @Column(name = "rule_type")
    private String rule_type; // 综测大类

    @Column(name = "rule_detail")
    private String rule_detail; // 细则

    @Column(name = "rule_score")
    private Float rule_score; // 细则分数

    @Column(name = "check_score")
    private Float check_score; // 审核分数

    @Column(name = "rule_accept")
    private Integer rule_accept; // 是否加入综测，默认为0, not null
}

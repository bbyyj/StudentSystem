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
@Table(name = "software")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name; // 软件著作权名称

    @Column(name = "inv_type")
    private String inv_type; // 第几发明人

    @Column(name = "inventors")
    private String inventors; // 全部完成人

    @Column(name = "app_status")
    private String app_status; // 申请状态

    @Column(name = "status_date")
    private Date status_date; // 该状态时间

    @Column(name = "remark")
    private String remark; // 备注

    @Column(name = "url")
    private String url; // 材料链接

    @Column(name = "check_status")
    private String check_status; // 审核状态

    @Column(name = "check_msg")
    private String check_msg; // 审核意见

    @Column(name = "sid")
    private String sid; // 学生学号

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

}

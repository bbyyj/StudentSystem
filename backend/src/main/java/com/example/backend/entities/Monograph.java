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
@Table(name = "monograph")
public class Monograph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title; // 专著名称

    @Column(name = "author_type")
    private String author_type; // 第几作者

    @Column(name = "authors")
    private String authors; // 该著作全部作者

    @Column(name = "publisher")
    private String publisher; // 出版单位

    @Column(name = "category")
    private String category; // 著作类别

    @Column(name = "isbn")
    private String isbn; // ISBN

    @Column(name = "pub_date")
    private Date pub_date; // 出版时间

    @Column(name = "remark")
    private String remark; // 备注

    @Column(name = "sid")
    private String sid; // 学生学号

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

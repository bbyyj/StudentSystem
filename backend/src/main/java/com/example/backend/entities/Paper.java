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
@Table(name = "paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title; // 论文标题

    @Column(name = "author_type")
    private String author_type; // 作者类型

    @Column(name = "authors")
    private String authors; // 全部作者姓名

    @Column(name = "cor_author")
    private String cor_author; // 通讯作者

    @Column(name = "jc_name")
    private String jc_name; // 发表期刊或会议

    @Column(name = "issn_cn")
    private String issn_cn; // ISSN/CN

    @Column(name = "impact_factor")
    private double impact_factor; // 影响因子

    @Column(name = "is_award")
    private boolean is_award; // 是否有最佳论文奖

    @Column(name = "pub_status")
    private String pub_status; // 发表状态

    @Column(name = "sub_date")
    private Date sub_date; // 投稿日期

    @Column(name = "acc_date")
    private Date acc_date; // 接收日期

    @Column(name = "pub_date")
    private Date pub_date; // 发表日期

    @Column(name = "page")
    private String page; // 卷（期）/页码范围

    @Column(name = "doi_pmid")
    private String doi_pmid; // DOI/PMID

    @Column(name = "paper_type")
    private String paper_type; // 论文类型

    @Column(name = "ccf_level")
    private String ccf; // CCF推荐情况

    @Column(name = "sci_level")
    private String sci; // 中科院分区

    @Column(name = "index")
    private String index; // 期刊收录情况:CSSCI / CSCD / SCI / SSCI / EI / A&HCI / 其他

    @Column(name = "publisher")
    private String publisher; // 出版单位

    @Column(name = "language")
    private String language; // 论文语言

    @Column(name = "is_internation")
    private String is_internation; // 是否国际合作论文

    @Column(name = "is_institution")
    private String is_institution; // 是否本单位合作论文

    @Column(name = "remark")
    private String remark; // 备注
}

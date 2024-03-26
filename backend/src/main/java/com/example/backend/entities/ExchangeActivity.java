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
@Table(name = "exchange_activity")
public class ExchangeActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name; // 项目名称

    @Column(name = "type")
    private String type; // 事由类型

    @Column(name = "funding")
    private String funding; // 经费来源

    @Column(name = "dest_country")
    private String dest_country; // 前往国家/地区

    @Column(name = "dest_city")
    private String dest_city; // 前往城市

    @Column(name = "dest_institution")
    private String dest_institution; // 前往院校/机构名称或竞赛/学术会议名称

    @Column(name = "duration")
    private String duration; // 项目期限

    @Column(name = "begin")
    private Date begin; // 出发时间

    @Column(name = "end")
    private Date end; // 返校时间

    @Column(name = "status")
    private String status; // 目前状态

    @Column(name = "is_apply")
    private boolean is_apply; // 是否已完成申报/报备

    @Column(name = "is_pre_edu")
    private boolean is_pre_edu; // 是否已参加行前教育

    @Column(name = "is_signed")
    private boolean is_signed; // 是否已签订《学生赴校外交流学习承诺书》《学生声明》《亲属声明》

    @Column(name = "is_talk")
    private boolean is_talk; // 是否已开展谈心谈话

    @Column(name = "remark")
    private String remark; // 若“事由类型”选其他，请备注
}

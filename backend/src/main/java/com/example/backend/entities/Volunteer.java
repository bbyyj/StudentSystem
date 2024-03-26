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
    private int time; // 志愿服务时长（X小时）
}

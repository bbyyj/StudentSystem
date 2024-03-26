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
@Table(name = "achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category")
    private String category; // 成果类别

    @Column(name = "date")
    private Date date; // 获得时间

    @Column(name = "comp_name")
    private String comp_name; // 比赛名称

    @Column(name = "achievement_name")
    private String achievement_name; // 成果名称

    @Column(name = "award_name")
    private String award_name; // 获奖名称

    @Column(name = "level")
    private String level; // 成果级别

    @Column(name = "grade")
    private String grade; // 成果等级

    @Column(name = "organization")
    private String organization; // 评奖组织单位

    @Column(name = "others")
    private String others; // 其他参与人员
}



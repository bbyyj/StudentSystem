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
@Table(name = "patent") // 修改表名为 "patents"
public class Patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name; // 专利名称

    @Column(name = "apply_number")
    private String apply_number; // 专利申请号

    @Column(name = "certificate_number")
    private String certificate_number; // 专利证书编号

    @Column(name = "inventors")
    private String inventors; // 全部发明人

    @Column(name = "type")
    private String type; // 专利类型

    @Column(name = "is_accept")
    private boolean is_accept; // 是否已受理

    @Column(name = "acc_date")
    private Date acc_date; // 受理时间

    @Column(name = "is_public")
    private boolean is_public; // 是否已公开

    @Column(name = "pub_date")
    private Date pub_date; // 公开时间

    @Column(name = "is_auth")
    private boolean is_auth; // 是否已授权

    @Column(name = "auth_date")
    private Date auth_date; // 授权时间

    @Column(name = "is_transfer")
    private boolean is_transfer; // 是否已转让

    @Column(name = "trans_date")
    private Date trans_date; // 转让时间

    @Column(name = "trans_income")
    private double trans_income; // 转让收入

    @Column(name = "remark")
    private String remark; // 备注
}


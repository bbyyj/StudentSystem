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
}

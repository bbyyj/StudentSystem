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
@Table(name = "basic_info")
public class BasicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "sex")
    private String sex;

    @Column(name = "sid")   // 学号
    private String sid;

    @Column(name = "cid")   // 身份证号
    private String cid;

    @Column(name = "birth")  //出生年月
    private Date birth;

    @Column(name = "class_id")
    private String class_id;

    @Column(name = "edu_level")
    private String edu_level;

    @Column(name = "pol_status")    //政治面貌
    private String pol_status;

    @Column(name = "dormitory")    //宿舍号
    private String dormitory;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "detail_addr")   //家庭详细地址
    private String detail_addr;

    @Column(name = "emergency_name")    //紧急联系人姓名
    private String emergency_name;

    @Column(name = "emergency_phone")   //紧急联系人电话
    private String emergency_phone;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "mail")
    private String mail;
}

package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //登录
    private String netId;
    private String password;

    @Column(name = "student_role")
    @Enumerated(EnumType.STRING)
    private StudentRole studentRole;//学生身份
    @Column(name = "is_undergraduate")
    private boolean isUndergraduate;//管理本科生/研究生
    @Column(name = "admission_year")
    private Year admissionYear; //入学年份
    @Column(name = "class_id")
    private Integer classId; //班级


    private String name;//姓名
    private String type;//港澳/境内/国际生
    private String nation;//民族
    private String sid;//学号
    private String pid;//身份证号
    private String birth;//出生年月
    private String politics;//政治面貌
    private String dormitory;
    @Column(name = "native_place")
    private String nativePlace;
    private String phone;
    private String address;
    @Column(name = "urgent_phone")
    private String urgentPhone;
    @Column(name = "urgent_name")
    private String urgentName;
    private String wechat;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(studentRole.name()));
    }

    @Override
    public String getUsername() {
        return netId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

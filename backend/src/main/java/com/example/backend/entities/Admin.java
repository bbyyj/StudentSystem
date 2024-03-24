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
@Table(name = "admin")
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //登录
    private String netId;
    private String password;

    @Column(name = "admin_role")
    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;//管理员身份
    @Column(name = "is_undergraduate")
    private boolean isUndergraduate;//管理本科生/研究生
    @Column(name = "admission_year")
    private Year admissionYear; //入学年份
    @Column(name = "class_id")
    private int classId; //班级

    private String tid;//职工号
    private String name;//姓名

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(adminRole.name()));
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

package com.example.backend.dao.request;

import com.example.backend.entities.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Year;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignUpRequest {
    //登录
    private String netId;
    private String password;

    private AdminRole adminRole;//管理员身份
    private boolean isUndergraduate;//管理本科生/研究生
    private Year admissionYear; //入学年份
    private int classId; //班级

    private String tid;//职工号
    private String name;//姓名
}

package com.example.backend.dao.request;

import com.example.backend.entities.StudentRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StuInfoModifyRequest {
    private String netId;
    private StudentRole studentRole;//学生身份
    private boolean isUndergraduate;//管理本科生/研究生
    private Year admissionYear; //入学年份
    private Integer classId; //班级
    private String name;//姓名
    private String type;//港澳/境内/国际生
    private String nation;//民族
    private String sid;//学号
    private String pid;//身份证号
    private String birth;//出生年月
    private String politics;//政治面貌
    private String nativePlace;
    private String dormitory;
}



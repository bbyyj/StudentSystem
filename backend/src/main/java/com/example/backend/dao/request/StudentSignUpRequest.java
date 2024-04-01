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
public class StudentSignUpRequest {

    private String netId;
    private String password;

    private StudentRole studentRole;
    private boolean isUndergraduate;
    private Year admissionYear;
    private int classId;

    private String sid;
    private String name;
}

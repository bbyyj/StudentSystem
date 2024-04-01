package com.example.backend.dao.response;

import com.example.backend.entities.Student;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentListResponse {
    private List<Student> studentList;
    private int count;
}

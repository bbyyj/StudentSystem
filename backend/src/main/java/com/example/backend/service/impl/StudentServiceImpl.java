package com.example.backend.service.impl;

import com.example.backend.repository.StudentRepository;
import com.example.backend.entities.Student;
import com.example.backend.entities.StudentRole;
import com.example.backend.service.StudentService;
import com.example.backend.dao.request.StudentSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;
import java.time.Year;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public UserDetailsService studentDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String netId) {
                return studentRepository.findByNetId(netId)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public ResponseEntity<String> uploadExcelFile(MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.ms-excel") &&
                !file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Only Excel files are allowed.");
        }
        try {
            List<Student> students = parseExcel(file.getInputStream());
            saveStudentsToDatabase(students);
            return ResponseEntity.ok("Students generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the file.");
        }
    }

    private List<Student> parseExcel(InputStream inputStream) throws IOException {
        List<Student> students = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String netId = row.getCell(0).getStringCellValue();
                String undergraduateType = row.getCell(1).getStringCellValue();
                boolean isUndergraduate = "本科生".equals(undergraduateType) ? true : false;
                int admissionYear = (int) row.getCell(2).getNumericCellValue();
                int classId = (int) row.getCell(3).getNumericCellValue();

                Student student = new Student();
                student.setNetId(netId);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encryptedPassword = passwordEncoder.encode("123456");
                student.setPassword(encryptedPassword);
                StudentRole studentRole = StudentRole.valueOf("NOT_MONITOR");
                student.setStudentRole(studentRole);
                student.setUndergraduate(isUndergraduate);
                student.setAdmissionYear(Year.of(admissionYear));
                student.setClassId(classId);

                students.add(student);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return students;
    }

    private void saveStudentsToDatabase(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public List<Student> getAllStudent() {
        System.out.println("-----------------------place 3------------------");
        return studentRepository.findAll();
    }
}

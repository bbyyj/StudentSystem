package com.example.backend.service.impl;

import com.example.backend.entities.Admin;
import com.example.backend.entities.Student;
import com.example.backend.entities.StudentRole;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String netId) {
                // 首先尝试从 adminRepository 中查找用户
                Optional<Admin> adminOptional = adminRepository.findByNetId(netId);

                // 如果在 adminRepository 中找到用户，则返回该用户
                if (adminOptional.isPresent()) {
                    Admin admin = adminOptional.get();
                    return new User(admin.getNetId(), admin.getPassword(), admin.getAuthorities());
                }

                // 如果在 adminRepository 中找不到用户，则尝试从 studentRepository 中查找用户
                Optional<Student> studentOptional = studentRepository.findByNetId(netId);

                // 如果在 studentRepository 中找到用户，则返回该用户
                if (studentOptional.isPresent()) {
                    Student student = studentOptional.get();
                    return new User(student.getNetId(), student.getPassword(), student.getAuthorities());
                }
                // 如果在 adminRepository 和 studentRepository 中都找不到用户，则抛出 UsernameNotFoundException
                throw new UsernameNotFoundException("User not found with netId: " + netId);
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

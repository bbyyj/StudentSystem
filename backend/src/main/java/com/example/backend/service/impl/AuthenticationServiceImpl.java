package com.example.backend.service.impl;

import com.example.backend.dao.request.AdminSignUpRequest;
import com.example.backend.dao.request.AdminSigninRequest;
import com.example.backend.dao.request.StudentSignUpRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.response.AdminInformationResponse;
import com.example.backend.dao.response.JwtAuthenticationResponse;
import com.example.backend.dao.response.StudentInformationResponse;
import com.example.backend.entities.Admin;
import com.example.backend.entities.Student;
import com.example.backend.entities.StudentRole;
import com.example.backend.repository.AdminRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signupAdmin(AdminSignUpRequest request) {
        var admin = Admin.builder()
                .netId(request.getNetId())
                .password(passwordEncoder.encode(request.getPassword()))
                .adminRole(request.getAdminRole())
                .isUndergraduate(request.isUndergraduate())
                .admissionYear(request.getAdmissionYear())
                .classId(request.getClassId())
                .tid(request.getTid())
                .name(request.getName())
                .build();
        adminRepository.save(admin);
        var jwt = jwtService.generateToken(admin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signinAdmin(AdminSigninRequest request) {
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(request.getNetId(), request.getPassword());
        authenticationManager.authenticate(upat);
        var admin = adminRepository.findByNetId(request.getNetId())
                .orElseThrow(() -> new IllegalArgumentException("Netid not found"));
        var jwt = jwtService.generateToken(admin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signupStudent(StudentSignUpRequest request) {
        var student = Student.builder()
                .netId(request.getNetId())
                .password(passwordEncoder.encode(request.getPassword()))
                .studentRole(request.getStudentRole())
                .isUndergraduate(request.isUndergraduate())
                .admissionYear(request.getAdmissionYear())
                .classId(request.getClassId())
                .sid(request.getSid())
                .name(request.getName())
                .build();
        studentRepository.save(student);
        var jwt = jwtService.generateToken(student);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signinStudent(StudentSigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNetId(), request.getPassword()));
        var student = studentRepository.findByNetId(request.getNetId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid netId or password."));
        var jwt = jwtService.generateToken(student);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public AdminInformationResponse getAdminInfomation(String netId) {
        Optional<Admin> adminOptional = adminRepository.findByNetId(netId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            AdminInformationResponse response = new AdminInformationResponse();
            response.setAdmin(admin);
            return response;
        } else {
            throw new IllegalArgumentException("Admin not found for netId: " + netId);
        }
    }


    public StudentInformationResponse getStudentInfomation(String netId) {
        Optional<Student> studentOptional = studentRepository.findByNetId(netId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            StudentInformationResponse response = new StudentInformationResponse();
            response.setStudent(student);
            return response;
        } else {
            throw new IllegalArgumentException("Student not found for netId: " + netId);
        }
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

//    private List<Student> parseExcel(InputStream inputStream) throws IOException {
//        List<Student> students = new ArrayList<>();
//        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
//            Sheet sheet = workbook.getSheetAt(0);
//            System.out.println("place -1");
//            Row headerRow = sheet.getRow(0);
//            System.out.println("place -2");
//
//            Map<String, Integer> columnIndexMap = new HashMap<>();
//            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//                System.out.println("place -3");
//
//                Cell cell = headerRow.getCell(i);
//                System.out.println("place -4");
//
//                String columnHeader = cell.getStringCellValue();
//                System.out.println("place -5");
//
//                columnIndexMap.put(columnHeader, i);
//            }
//
//            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//                Row row = sheet.getRow(rowIndex);
//                System.out.println("place 1");
//                String netId = row.getCell(columnIndexMap.get("netId")).getStringCellValue();
//                System.out.println("place 2");
//
//                String undergraduateType = row.getCell(columnIndexMap.get("培养层次")).getStringCellValue();
//                System.out.println("place 3");
//
//                boolean isUndergraduate = "本科生".equals(undergraduateType);
//                System.out.println("place 4");
//
//                Cell admissionYearCell = row.getCell(columnIndexMap.get("入学年份"));
//                System.out.println("place 5");
//
//                String admissionYearStr;
//                if (admissionYearCell.getCellType() == CellType.NUMERIC) {
//                    admissionYearStr = String.valueOf((int) admissionYearCell.getNumericCellValue());
//                } else {
//                    admissionYearStr = admissionYearCell.getStringCellValue();
//                }
//                System.out.println("place 6");
//
//                Year admissionYear = Year.parse(admissionYearStr);
//                System.out.println("place 7");
//
//                Cell classIdCell = row.getCell(columnIndexMap.get("班级"));
//                System.out.println("place 8");
//
//                int classId;
//                System.out.println("place 9");
//
//                if (classIdCell.getCellType() == CellType.NUMERIC) {
//                    classId = (int) classIdCell.getNumericCellValue();
//                } else {
//                    classId = Integer.parseInt(classIdCell.getStringCellValue());
//                }
//                System.out.println("place 10");
//
//
//                Student student = new Student();
//                student.setNetId(netId);
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                String encryptedPassword = passwordEncoder.encode("123456");
//                student.setPassword(encryptedPassword);
//                StudentRole studentRole = StudentRole.NOT_MONITOR;
//                student.setStudentRole(studentRole);
//                student.setUndergraduate(isUndergraduate);
//                student.setAdmissionYear(admissionYear);
//                student.setClassId(classId);
//
//                students.add(student);
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return students;
//    }


    private void saveStudentsToDatabase(List<Student> students) {
        studentRepository.saveAll(students);
    }
}

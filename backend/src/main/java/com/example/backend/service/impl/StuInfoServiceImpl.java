package com.example.backend.service.impl;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.request.StudentUpdateDormitoryRequest;
import com.example.backend.entities.Student;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.StuInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StuInfoServiceImpl implements StuInfoService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> importStudentDormitory(MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.ms-excel") &&
                !file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Only Excel files are allowed.");
        }
        try {
            List<StudentUpdateDormitoryRequest> updates = parseDormitoryUpdatesFromExcel(file.getInputStream());
            updateDormitoriesInDatabase(updates);
            return ResponseEntity.ok("Dormitory information updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the file.");
        }
    }

    private List<StudentUpdateDormitoryRequest> parseDormitoryUpdatesFromExcel(InputStream inputStream) throws IOException {
        List<StudentUpdateDormitoryRequest> updates = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String netId = row.getCell(0).getStringCellValue();
                String dormitory = row.getCell(1).getStringCellValue();
                StudentUpdateDormitoryRequest update = new StudentUpdateDormitoryRequest(netId, dormitory);
                updates.add(update);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return updates;
    }

    private void updateDormitoriesInDatabase(List<StudentUpdateDormitoryRequest> updates) {
        for (StudentUpdateDormitoryRequest update : updates) {
            studentRepository.updateDormitoryByNetId(update.getNetId(), update.getDormitory());
        }
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> getClassStudent(Boolean isUndergraduate, Year admissionYear, Integer classId) {
        return studentRepository.findByIsUndergraduateAndAdmissionYearAndClassId(isUndergraduate, admissionYear, classId);
    }

    public ResponseEntity<String> modifyMyInfo(MyInfoModifyRequest request) {
        try {
            studentRepository.updateMyInfo(
                    request.getNetId(),
                    request.getEmail(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getUrgentPhone(),
                    request.getUrgentName(),
                    request.getWechat()
            );
            return ResponseEntity.ok("My information modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while modifying student information.");
        }
    }

    public ResponseEntity<String> modifyStuInfo(StuInfoModifyRequest request) {
        try {
            studentRepository.updateStuInfo(
                    request.getNetId(),
                    request.getStudentRole(),
                    request.isUndergraduate(),
                    request.getAdmissionYear(),
                    request.getClassId(),
                    request.getName(),
                    request.getType(),
                    request.getNation(),
                    request.getSid(),
                    request.getPid(),
                    request.getBirth(),
                    request.getPolitics(),
                    request.getNativePlace(),
                    request.getDormitory()
            );
            return ResponseEntity.ok("Student information modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while modifying student information.");
        }
    }

    public ResponseEntity<String> modifyPassword(StudentSigninRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        try{
            studentRepository.updatePasswordByNetId(request.getNetId(), password);
            return ResponseEntity.ok("Password modified successfully.");
        }catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while modifying password.");
        }
    }

    public ResponseEntity<String> deleteStuInfo(String netId) {
        try {
            studentRepository.deleteByNetId(netId);
            return new ResponseEntity<>("Student with netId " + netId + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to delete student with netId " + netId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Student> searchStuInfo(String keyword) {
        return studentRepository.findByKeyword(keyword);
    }

}

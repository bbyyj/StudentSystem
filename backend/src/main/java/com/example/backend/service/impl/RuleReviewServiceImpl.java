package com.example.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.request.ReviewAddRequest;
import com.example.backend.dao.request.StuUpdateExcelRequest;
import com.example.backend.dao.request.StudentReviewListRequest;
import com.example.backend.entities.Review;
import com.example.backend.entities.Student;
import com.example.backend.entities.StudentReviewList;
//import com.example.backend.entities.StudentReviewListDTO;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.StudentReviewListRepository;
import com.example.backend.service.RuleReviewService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import com.example.backend.utils.Utils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RuleReviewServiceImpl implements RuleReviewService {
    private final ReviewRepository  reviewRepository;
    private final StudentReviewListRepository  studentReviewListRepository;
    private final StudentRepository  studentRepository;
    private final Utils u;
    public JSONObject addReview(ReviewAddRequest request) {
        Review review = new Review();
        review.setName(request.getName());
        review.setStart_time(Date.valueOf(request.getStart_time()));
        review.setEnd_time(Date.valueOf(request.getEnd_time()));
        review = reviewRepository.saveAndFlush(review);
        int id = review.getId();
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("id", id);

        return jsonResponse;
    }

    @Override
    public ResponseEntity<String> updateReview(ReviewAddRequest request) {
        try {
            reviewRepository.update(request.getId(),request.getName(),Date.valueOf(request.getStart_time()),Date.valueOf(request.getEnd_time()));
            return ResponseEntity.ok("review update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating review.");
        }
    }

    @Override
    public Page<Review> getReviews(int page,int size) {
        Pageable pageable= PageRequest.of(page, size);
        return reviewRepository.getAll(pageable);
    }

    @Override
    public Page<Review> getReviewsByCondition(String name, int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return reviewRepository.getByCondition(name,pageable);
    }

    @Override
    public Page<StudentReviewList> getStudentReviewList(StudentReviewListRequest request,int page,int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<StudentReviewList> studentReviewLists = studentReviewListRepository.findByReviewId(request.getId(),request.getIsUndergraduate(),request.getAdmission_year(),request.getClassId(),pageable);
        if(studentReviewLists.isEmpty()){
            List<Student>  students = studentRepository.findByIsUndergraduateAndAdmissionYearAndClassId(request.getIsUndergraduate(), request.getAdmission_year(), request.getClassId());
            for(Student student:students){
                StudentReviewList studentReviewList = new StudentReviewList();
                studentReviewList.setReview_id(request.getId());
                studentReviewList.setStudent_id(student.getNetId());
                studentReviewList.setStudent_name(student.getName());
                studentReviewList.setUndergraduate(student.isUndergraduate());
                studentReviewList.setAdmission_year(student.getAdmissionYear());
                studentReviewList.setClassId(student.getClassId());
                studentReviewListRepository.saveAndFlush(studentReviewList);
            }

            studentReviewLists = studentReviewListRepository.findByReviewId(request.getId(),request.getIsUndergraduate(),request.getAdmission_year(),request.getClassId(),pageable);

        }
        return studentReviewLists;
    }

    @Override
    public Page<StudentReviewList> getStudentReviewListByCondition(StudentReviewListRequest request, int page, int size, String state, String sid, String sname) {
        Pageable pageable= PageRequest.of(page, size);
        return studentReviewListRepository.findByReviewIdAndCondition(request.getId(),request.getIsUndergraduate(),request.getAdmission_year(),request.getClassId(),state,sid,sname,pageable);
    }

    @Override
    public Page<Map<String, Object>> getStudentMatiarial(int review_id, String student_id,int page,int size) {
        Review review = reviewRepository.findById(review_id);
        System.out.println(review.getStart_time());
        System.out.println(review.getEnd_time());
        return u.getInfoFromTable(student_id,review.getStart_time(),review.getEnd_time(),page,size);
    }

    @Override
    public ResponseEntity<String> submitReview(List<Map<String, Object>> data) {
        try {
            //先更新
            for (Map<String, Object> entry : data) {
                if (entry.containsKey("name") && entry.containsKey("source") && entry.containsKey("others")) {
                    if(entry.get("rule_accept")!=null){
                        u.updateRuleAcceptByTableName(entry.get("source").toString(), (Integer) entry.get("id"), (Integer) entry.get("rule_accept"));
                    }
                    if(entry.get("check_score")!=null){
                        Object obj = entry.get("check_score");
                        double checkScore = 0.0; // 默认值
                        if (obj instanceof Number) {
                            checkScore = ((Number) obj).doubleValue();
                        }
                        u.updateCheckScoreByTableName(entry.get("source").toString(), (Integer) entry.get("id"), checkScore);
                    }


                }
            }
            //自动算分
            List<Map<String, Object>> filteredData = data.stream()
                    .filter(map -> map.containsKey("rule_accept") && map.get("rule_accept") != null && map.get("rule_accept").equals(1))
                    .collect(Collectors.toList());
            List<Map<String, Object>> DataMorality = filteredData.stream()
                    .filter(map -> map.containsKey("rule_type") && map.get("rule_type") != null && map.get("rule_type").equals("政治思想道德类"))
                    .collect(Collectors.toList());
            List<Map<String, Object>> DataWork = filteredData.stream()
                    .filter(map -> map.containsKey("rule_type") && map.get("rule_type") != null && map.get("rule_type").equals("社会工作类"))
                    .collect(Collectors.toList());
            List<Map<String, Object>> DataPractice = filteredData.stream()
                    .filter(map -> map.containsKey("rule_type") && map.get("rule_type") != null && map.get("rule_type").equals("文体、实践类"))
                    .collect(Collectors.toList());
            List<Map<String, Object>> DataStudy = filteredData.stream()
                    .filter(map -> map.containsKey("rule_type") && map.get("rule_type") != null && map.get("rule_type").equals("学习、竞赛及科研成果"))
                    .collect(Collectors.toList());
            float scoreMorality=0;
            for (Map<String, Object> entry : DataMorality) {
                if (entry.get("check_score")!=null) {
                    Object obj = entry.get("check_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreMorality+=checkScore;
                }else if(entry.get("rule_score")!=null){
                    Object obj = entry.get("rule_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreMorality+=checkScore;
                }
            }
            if(scoreMorality>3.0){
                scoreMorality=3;
            }
            float scoreWork=0;
            for (Map<String, Object> entry : DataWork) {
                if (entry.get("check_score")!=null) {
                    Object obj = entry.get("check_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreWork+=checkScore;
                }else if(entry.get("rule_score")!=null){
                    Object obj = entry.get("rule_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreWork+=checkScore;
                }
            }
            if(scoreWork>3.0){
                scoreWork=3;
            }
            float scorePractice=0;
            for (Map<String, Object> entry : DataPractice) {
                if (entry.get("check_score")!=null) {
                    Object obj = entry.get("check_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scorePractice+=checkScore;
                }else if(entry.get("rule_score")!=null){
                    Object obj = entry.get("rule_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scorePractice+=checkScore;
                }
            }
            if(scorePractice>4.0){
                scorePractice=4;
            }
            float scoreStudy=0;
            for (Map<String, Object> entry : DataStudy) {
                if (entry.get("check_score")!=null) {
                    Object obj = entry.get("check_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreStudy+=checkScore;
                }else if(entry.get("rule_score")!=null){
                    Object obj = entry.get("rule_score");
                    double checkScore = 0.0; // 默认值
                    if (obj instanceof Number) {
                        checkScore = ((Number) obj).doubleValue();
                    }
                    scoreStudy+=checkScore;
                }
            }
            if(scoreStudy>6.0){
                scoreStudy=6;
            }
            double score=scoreMorality+scoreWork+scorePractice+scoreStudy;
            for (Map<String, Object> entry : data) {
                if (entry.containsKey("student_id")&&entry.containsKey("review_id") ) {
                    studentReviewListRepository.updateScoreAndState(score,(Integer) entry.get("review_id"),entry.get("student_id").toString());
                }
            }
            return ResponseEntity.ok("calculate score successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while calculate score.");
        }



    }

    @Override
    public Page<StudentReviewList> getAllStudentReviewList(int id, int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return studentReviewListRepository.findAll(id,pageable);
    }

    @Override
    public Page<StudentReviewList> getStudentReviewListByTeacher(int id, boolean isUndergraduate, Year admissionYear, int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return studentReviewListRepository.findByYear(id,isUndergraduate,admissionYear,pageable);
    }

    @Override
    public ResponseEntity<String> upLoadExamScore(int id,MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.ms-excel") &&
                !file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Only Excel files are allowed.");
        }
        try {
            parseInformationUpdatesFromExcel(id,file.getInputStream());
            return ResponseEntity.ok("Personal information updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the file.");
        }
    }

    private int getColumnIndex(Row headerRow, String columnName) {
        for (Cell cell: headerRow) {
            if (columnName.equals(cell.getStringCellValue())) {
                return cell.getColumnIndex();
            }
        }
        return 10000;
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue()); // 转换为字符串
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }

    private double getCellValueAsDouble(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            case FORMULA:
                return cell.getNumericCellValue();
            case BLANK:
                return 0;
            default:
                return 0;
        }
    }

    private void parseInformationUpdatesFromExcel(int id,InputStream inputStream) throws IOException {
        List<StuUpdateExcelRequest> updates = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int sidIndex = getColumnIndex(headerRow, "学号");
            int examScoreIndex = getColumnIndex(headerRow, "成绩");

            if(sidIndex != 10000)
            {
                if(examScoreIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        Cell studentIdCell = row.getCell(0); // 假设学号在第一列
                        Cell scoreCell = row.getCell(1); // 假设成绩在第二列

                        String studentId = getCellValueAsString(studentIdCell);
                        double examScore = (double) getCellValueAsDouble(scoreCell);
                        Optional<StudentReviewList> optionalStudentReviewList=studentReviewListRepository.findByReviewIdAndStudentId(id,studentId);
                        if (optionalStudentReviewList.isPresent()) {
                            double score=optionalStudentReviewList.get().getScore();
                            studentReviewListRepository.updateExamScore(examScore,examScore+score,id,studentId);
                        }
                    }

                }

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

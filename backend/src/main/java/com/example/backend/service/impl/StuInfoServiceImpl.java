package com.example.backend.service.impl;

import com.example.backend.dao.request.MyInfoModifyRequest;
import com.example.backend.dao.request.StuInfoModifyRequest;
import com.example.backend.dao.request.StuUpdateExcelRequest;
import com.example.backend.dao.request.StudentSigninRequest;
import com.example.backend.dao.request.StuUpdateExcelRequest;
import com.example.backend.dao.response.CombinedCA;
import com.example.backend.entities.*;
import com.example.backend.repository.*;
import com.example.backend.service.StuInfoService;
import com.example.backend.utils.ConvertEntityUtil;
import com.example.backend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class StuInfoServiceImpl implements StuInfoService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompetitionRepository competitionRepository;
    private final PaperRepository paperRepository;
    private final PatentRepository patentRepository;
    private final SoftwareRepository softwareRepository;
    private final MonographRepository monographRepository;
    private final ExchangeActivityRepository activityRepository;
    private final VolunteerRepository volunteerRepository;
    private final SocialWorkRepository socialWorkRepository;
    private final IdeologyMoralityRepository ideologyMoralityRepository;
    private final OthersRepository othersRepository;


    private int getColumnIndex(Row headerRow, String columnName) {
        for (Cell cell: headerRow) {
            if (columnName.equals(cell.getStringCellValue())) {
                return cell.getColumnIndex();
            }
        }
        return 10000;
    }

    private void updateDormitoriesInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updateDormitoryBySid(update.getSid(), update.getDormitory());
        }
    }

    private void updateNamesInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updateNameBySid(update.getSid(), update.getName());
        }
    }

    private void updateTypesInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updateTypeBySid(update.getSid(), update.getType());
        }
    }

    private void updateNationsInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updateNationBySid(update.getSid(), update.getNation());
        }
    }

    private void updatePidsInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updatePidBySid(update.getSid(), update.getPid());
        }
    }

    private void updateBirthsInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updateBirthBySid(update.getSid(), update.getBirth());
        }
    }

    private void updatePoliticssInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
            studentRepository.updatePoliticsBySid(update.getSid(), update.getPolitics());
        }
    }

    private void updateNativePlacesInDatabase(List<StuUpdateExcelRequest> updates) {
        for (StuUpdateExcelRequest update : updates) {
                studentRepository.updateNativePlaceBySid(update.getSid(), update.getNativePlace());
        }
    }

    public ResponseEntity<String> modifyStuInfoExcel(MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.ms-excel") &&
                !file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.badRequest().body("Only Excel files are allowed.");
        }
        try {
            parseInformationUpdatesFromExcel(file.getInputStream());
            return ResponseEntity.ok("Personal information updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the file.");
        }
    }



    private void parseInformationUpdatesFromExcel(InputStream inputStream) throws IOException {
        List<StuUpdateExcelRequest> updates = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int sidIndex = getColumnIndex(headerRow, "学号");
            int dormitoryIndex = getColumnIndex(headerRow, "宿舍");
            int nameIndex = getColumnIndex(headerRow, "姓名");
            int typeIndex = getColumnIndex(headerRow, "学生类别");
            int nationIndex = getColumnIndex(headerRow, "民族");
            int pidIndex = getColumnIndex(headerRow, "身份证号");
            int birthIndex = getColumnIndex(headerRow, "出生年月");
            int politicsIndex = getColumnIndex(headerRow, "政治面貌");
            int nativePlaceIndex = getColumnIndex(headerRow, "籍贯");

            if(sidIndex != 10000)
            {
                if(dormitoryIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String dormitory = dataFormatter.formatCellValue(row.getCell(dormitoryIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, dormitory, "","","","","","","");
                        updates.add(update);
                    }
                    updateDormitoriesInDatabase(updates);
                }
                if(nameIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String name = dataFormatter.formatCellValue(row.getCell(nameIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", name,"","","","","","");
                        updates.add(update);
                    }
                    updateNamesInDatabase(updates);
                }
                if(typeIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String type = dataFormatter.formatCellValue(row.getCell(typeIndex), formulaEvaluator);
                        System.out.println(sid);
                        System.out.println(type);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "",type,"","","","","");
                        updates.add(update);
                    }
                    updateTypesInDatabase(updates);
                }
                if(nationIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String nation = dataFormatter.formatCellValue(row.getCell(nationIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "","",nation,"","","","");
                        updates.add(update);
                    }
                    updateNationsInDatabase(updates);
                }
                if(pidIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String pid = dataFormatter.formatCellValue(row.getCell(pidIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "","","",pid,"","","");
                        updates.add(update);
                    }
                    updatePidsInDatabase(updates);
                }
                if(birthIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String birth = dataFormatter.formatCellValue(row.getCell(birthIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "","","","",birth,"","");
                        updates.add(update);
                    }
                    updateBirthsInDatabase(updates);
                }
                if(politicsIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String politics = dataFormatter.formatCellValue(row.getCell(politicsIndex), formulaEvaluator);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "","","","","",politics,"");
                        updates.add(update);
                    }
                    updatePoliticssInDatabase(updates);
                }
                if(nativePlaceIndex != 10000)
                {
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        String sid = dataFormatter.formatCellValue(row.getCell(sidIndex), formulaEvaluator);
                        String nativePlace = dataFormatter.formatCellValue(row.getCell(nativePlaceIndex), formulaEvaluator);
                        System.out.println(sid);
                        System.out.println(nativePlace);
                        StuUpdateExcelRequest update = new StuUpdateExcelRequest(sid, "", "","","","","","",nativePlace);
                        updates.add(update);
                    }
                    updateNativePlacesInDatabase(updates);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public Page<Student> findByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Page<Student> getClassStudent(Boolean isUndergraduate, Year admissionYear, Integer classId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findByIsUndergraduateAndAdmissionYearAndClassId(isUndergraduate, admissionYear, classId, pageable);
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

    public Page<Student> searchStuInfo(String keyname, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        switch (keyname) {
            case "name":
                return studentRepository.findByNameKeyword(keyword, pageable);
            case "sid":
                return studentRepository.findBySidKeyword(keyword, pageable);
            case "type":
                return studentRepository.findByTypeKeyword(keyword, pageable);
            case "nation":
                return studentRepository.findByNationKeyword(keyword, pageable);
            case "pid":
                return studentRepository.findByPidKeyword(keyword, pageable);
            case "birth":
                return studentRepository.findByBirthKeyword(keyword, pageable);
            case "politics":
                return studentRepository.findByPoliticsKeyword(keyword, pageable);
            case "dormitory":
                return studentRepository.findByDormitoryKeyword(keyword, pageable);
            case "nativePlace":
                return studentRepository.findByNativePlaceKeyword(keyword, pageable);
            case "phone":
                return studentRepository.findByPhoneKeyword(keyword, pageable);
            case "address":
                return studentRepository.findByAddressKeyword(keyword, pageable);
            case "urgentPhone":
                return studentRepository.findByUrgentPhoneKeyword(keyword, pageable);
            case "wechat":
                return studentRepository.findByWechatKeyword(keyword, pageable);
            case "email":
                return studentRepository.findByEmailKeyword(keyword, pageable);
            case "classId":
                return studentRepository.findByClassId(Integer.parseInt(keyword), pageable);
            case "admissionYear":
                return studentRepository.findByAdmissionYear(Integer.parseInt(keyword), pageable);
            case "isUndergraduate":
                return studentRepository.findByIsUndergraduate(Boolean.parseBoolean(keyword), pageable);
            case "studentRole":
                return studentRepository.findByStudentRole(keyword, pageable);
            default:
                throw new IllegalArgumentException("Invalid keyname: " + keyname);
        }
    }


    public List<CombinedCA> getMyCA(String sid, int page, int size) {
        ConvertEntityUtil Converter = new ConvertEntityUtil();

        List<Competition> competitions = competitionRepository.getCompetitionsBySid(sid);
        List<CombinedCA> competitionCAs = Converter.convertCompetitionsToCombinedCA(competitions);

        List<Paper> papers = paperRepository.getPaperBySid(sid);
        List<CombinedCA> paperCAs = Converter.convertPapersToCombinedCA(papers);

        List<Patent> patents = patentRepository.getPatentBySid(sid);
        List<CombinedCA> patentCAs = Converter.convertPatentsToCombinedCA(patents);

        List<ExchangeActivity> exchangeActivitys = activityRepository.getExchangeActivityBySid(sid);
        List<CombinedCA> exchangeActivityCAs = Converter.convertExchangeActivitiesToCombinedCA(exchangeActivitys);

        List<Volunteer> volunteers = volunteerRepository.getVolunteerBySid(sid);
        List<CombinedCA> volunteerCAs = Converter.convertVolunteersToCombinedCA(volunteers);

        List<Software> softwares = softwareRepository.getSoftwareBySid(sid);
        List<CombinedCA> softwareCAs = Converter.convertSoftwaresToCombinedCA(softwares);

        List<Monograph> monographs = monographRepository.getMonographBySid(sid);
        List<CombinedCA> monographCAs = Converter.convertMonographsToCombinedCA(monographs);

        List<SocialWork> socialWorks = socialWorkRepository.getSocialWorkBySid(sid);
        List<CombinedCA> socialWorkCAs = Converter.convertSocialWorksToCombinedCA(socialWorks);

        List<IdeologyMorality> ideologyMoralitys = ideologyMoralityRepository.getIdeologyMoralityBySid(sid);
        List<CombinedCA> ideologyMoralityCAs = Converter.convertIdeologyMoralitiesToCombinedCA(ideologyMoralitys);

        List<Others> others = othersRepository.getOthersBySid(sid);
        List<CombinedCA> othersCAs = Converter.convertOthersToCombinedCA(others);

        List<CombinedCA> allCAs = Stream.of(
                        competitionCAs, paperCAs, patentCAs, exchangeActivityCAs, volunteerCAs, softwareCAs, monographCAs,socialWorkCAs,ideologyMoralityCAs,othersCAs
                ).flatMap(combinedCAList -> combinedCAList.stream())
                .collect(Collectors.toList());

        List<CombinedCA> pagedCAs = ConvertEntityUtil.getPage(allCAs, size, page);

        return pagedCAs;

    }

}

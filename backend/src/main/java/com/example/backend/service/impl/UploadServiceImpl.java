package com.example.backend.service.impl;

import com.example.backend.entities.*;
import com.example.backend.repository.*;
import com.example.backend.service.UploadService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final Gson gson = new Gson();
    private final SoftwareRepository softwareRepository;
    private final CompetitionRepository competitionRepository;
    private final PaperRepository paperRepository;
    private final PatentRepository patentRepository;
    private final VolunteerRepository volunteerRepository;
    private final ExchangeActivityRepository exchangeActivityRepository;
    private final MonographRepository monographRepository;

    public void uploadSoftware(String url, String softwareJson){

        Software software = gson.fromJson(softwareJson, Software.class);

        software.setId(0);
        software.setUrl(url);
        software.setCheck_msg("无");
        software.setCheck_status("未审核");

        softwareRepository.save(software);

    }

    public void uploadCompetition(String url, String competitionJson){

        Competition competition = gson.fromJson(competitionJson, Competition.class);

        competition.setId(0);
        competition.setUrl(url);
        competition.setCheck_msg("无");
        competition.setCheck_status("未审核");

        competitionRepository.save(competition);

    }

    public void uploadPaper(String url, String paperJson){

        Paper paper = gson.fromJson(paperJson, Paper.class);

        paper.setId(0);
        paper.setUrl(url);
        paper.setCheck_msg("无");
        paper.setCheck_status("未审核");

        paperRepository.save(paper);

    }

    public void uploadPatent(String url, String patentJson){

        Patent patent = gson.fromJson(patentJson, Patent.class);

        patent.setId(0);
        patent.setUrl(url);
        patent.setCheck_msg("无");
        patent.setCheck_status("未审核");

        patentRepository.save(patent);

    }

    public void uploadVolunteer(String url, String volunteerJson){

        Volunteer volunteer = gson.fromJson(volunteerJson, Volunteer.class);

        volunteer.setId(0);
        volunteer.setUrl(url);
        volunteer.setCheck_msg("无");
        volunteer.setCheck_status("未审核");

        volunteerRepository.save(volunteer);

    }

    public void uploadMonograph(String url, String monographJson){

        Monograph monograph = gson.fromJson(monographJson, Monograph.class);

        monograph.setId(0);
        monograph.setUrl(url);
        monograph.setCheck_msg("无");
        monograph.setCheck_status("未审核");

        monographRepository.save(monograph);

    }

    public void uploadExchangeActivity(String url, String exchangeActivityJson){

        ExchangeActivity exchangeActivity = gson.fromJson(exchangeActivityJson, ExchangeActivity.class);

        exchangeActivity.setId(0);
        exchangeActivity.setUrl(url);
        exchangeActivity.setCheck_msg("无");
        exchangeActivity.setCheck_status("未审核");

        exchangeActivityRepository.save(exchangeActivity);

    }

//    public void uploadItem(String url, String itemJson, Class<?> itemType, JpaRepository repository) {
//        Object item = gson.fromJson(itemJson, itemType);
//        try {
//            Field idField = itemType.getDeclaredField("id");
//            idField.setAccessible(true);
//            idField.set(item, 0);
//
//            Field urlField = itemType.getDeclaredField("url");
//            urlField.setAccessible(true);
//            urlField.set(item, url);
//
//            Field checkMsgField = itemType.getDeclaredField("check_msg");
//            checkMsgField.setAccessible(true);
//            checkMsgField.set(item, "未审核");
//
//            Field checkStatusField = itemType.getDeclaredField("check_status");
//            checkStatusField.setAccessible(true);
//            checkStatusField.set(item, "无");
//
//            // 保存到数据库
//            repository.save((Object)item);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 在这里处理异常
//        }
//    }


}

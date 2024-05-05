package com.example.backend.service.impl;

import com.example.backend.entities.*;
import com.example.backend.repository.*;
import com.example.backend.service.UploadService;
import com.example.backend.service.RuleManageService;
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
    private final OthersRepository othersRepository;
    private final RuleManageService ruleManageService;


    public void uploadSoftware(String url, String softwareJson){

        Software software = gson.fromJson(softwareJson, Software.class);

        software.setId(0);
        software.setUrl(url);
        software.setCheck_msg("未审核");
        software.setCheck_status("无");
        software.setRule_score(ruleManageService.getRuleScore(software.getRule_type(),software.getRule_detail()));

        softwareRepository.save(software);

    }

    public void uploadCompetition(String url, String competitionJson){

        Competition competition = gson.fromJson(competitionJson, Competition.class);

        competition.setId(0);
        competition.setUrl(url);
        competition.setCheck_msg("未审核");
        competition.setCheck_status("无");
        competition.setRule_score(ruleManageService.getRuleScore(competition.getRule_type(),competition.getRule_detail()));

        competitionRepository.save(competition);

    }

    public void uploadPaper(String url, String paperJson){

        Paper paper = gson.fromJson(paperJson, Paper.class);

        paper.setId(0);
        paper.setUrl(url);
        paper.setCheck_msg("未审核");
        paper.setCheck_status("无");
        paper.setRule_score(ruleManageService.getRuleScore(paper.getRule_type(),paper.getRule_detail()));

        paperRepository.save(paper);

    }

    public void uploadPatent(String url, String patentJson){

        Patent patent = gson.fromJson(patentJson, Patent.class);

        patent.setId(0);
        patent.setUrl(url);
        patent.setCheck_msg("未审核");
        patent.setCheck_status("无");
        patent.setRule_score(ruleManageService.getRuleScore(patent.getRule_type(),patent.getRule_detail()));

        patentRepository.save(patent);

    }

    public void uploadVolunteer(String url, String volunteerJson){

        Volunteer volunteer = gson.fromJson(volunteerJson, Volunteer.class);

        volunteer.setId(0);
        volunteer.setUrl(url);
        volunteer.setCheck_msg("未审核");
        volunteer.setCheck_status("无");
        volunteer.setRule_score(ruleManageService.getRuleScore(volunteer.getRule_type(),volunteer.getRule_detail()));

        volunteerRepository.save(volunteer);

    }

    public void uploadMonograph(String url, String monographJson){

        Monograph monograph = gson.fromJson(monographJson, Monograph.class);

        monograph.setId(0);
        monograph.setUrl(url);
        monograph.setCheck_msg("未审核");
        monograph.setCheck_status("无");
        monograph.setRule_score(ruleManageService.getRuleScore(monograph.getRule_type(),monograph.getRule_detail()));

        monographRepository.save(monograph);

    }

    public void uploadExchangeActivity(String url, String exchangeActivityJson){

        ExchangeActivity exchangeActivity = gson.fromJson(exchangeActivityJson, ExchangeActivity.class);

        exchangeActivity.setId(0);
        exchangeActivity.setUrl(url);
        exchangeActivity.setCheck_msg("未审核");
        exchangeActivity.setCheck_status("无");
        exchangeActivity.setRule_score(ruleManageService.getRuleScore(exchangeActivity.getRule_type(),exchangeActivity.getRule_detail()));

        exchangeActivityRepository.save(exchangeActivity);

    }

    @Override
    public void uploadOthers(String url, String othersJson) {
        Others others = gson.fromJson(othersJson, Others.class);

        others.setId(0);
        others.setUrl(url);
        others.setCheck_msg("未审核");
        others.setCheck_status("无");
        others.setRule_score(ruleManageService.getRuleScore(others.getRule_type(),others.getRule_detail()));

        othersRepository.save(others);
    }

}

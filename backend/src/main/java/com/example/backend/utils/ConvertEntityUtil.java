package com.example.backend.utils;
import com.example.backend.dao.response.BasicCA;
import com.example.backend.dao.response.CombinedCA;
import com.example.backend.entities.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
public class ConvertEntityUtil {

    public static <T> List<T> getPage(List<T> allItems, int pageSize, int pageNumber) {
        int start = pageNumber * pageSize;
        int end = Math.min(start + pageSize, allItems.size());
        return allItems.subList(start, end);
    }

    public static List<CombinedCA> convertCompetitionsToCombinedCA(List<Competition> competitions) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Competition competition : competitions) {
            CombinedCA combinedCA = new CombinedCA();

            BasicCA basicCA = new BasicCA();
            basicCA.setSid(competition.getSid());
            basicCA.setType("比赛获奖");
            basicCA.setRule_type(competition.getRule_type());
            basicCA.setRule_detail(competition.getRule_detail());
            basicCA.setUrl(competition.getUrl());
            basicCA.setCheck_status(competition.getCheck_status());
            basicCA.setCheck_msg(competition.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("submit_time", competition.getSubmit_time());
            typeDetailCA.put("academic_year", competition.getAcademic_year());
            typeDetailCA.put("type", competition.getType());
            typeDetailCA.put("time", competition.getTime());
            typeDetailCA.put("competition_name", competition.getCompetition_name());
            typeDetailCA.put("award_name", competition.getAward_name());
            typeDetailCA.put("result_level", competition.getResult_level());
            typeDetailCA.put("result_type", competition.getResult_type());
            typeDetailCA.put("organization", competition.getOrganization());
            typeDetailCA.put("teammate", competition.getTeammate());
            typeDetailCA.put("captain", competition.getCaptain());
            typeDetailCA.put("rule_score", competition.getRule_score());
            typeDetailCA.put("check_score", competition.getCheck_score());
            typeDetailCA.put("rule_accept", competition.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertPapersToCombinedCA(List<Paper> papers) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Paper paper : papers) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(paper.getSid());
            basicCA.setType("论文发表");
            basicCA.setRule_type(paper.getRule_type());
            basicCA.setRule_detail(paper.getRule_detail());
            basicCA.setUrl(paper.getUrl());
            basicCA.setCheck_status(paper.getCheck_status());
            basicCA.setCheck_msg(paper.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("title", paper.getTitle());
            typeDetailCA.put("author_type", paper.getAuthor_type());
            typeDetailCA.put("authors", paper.getAuthors());
            typeDetailCA.put("cor_author", paper.getCor_author());
            typeDetailCA.put("jc_name", paper.getJc_name());
            typeDetailCA.put("issn_cn", paper.getIssn_cn());
            typeDetailCA.put("impact_factor", paper.getImpact_factor());
            typeDetailCA.put("is_award", paper.is_award());
            typeDetailCA.put("pub_status", paper.getPub_status());
            typeDetailCA.put("sub_date", paper.getSub_date());
            typeDetailCA.put("acc_date", paper.getAcc_date());
            typeDetailCA.put("pub_date", paper.getPub_date());
            typeDetailCA.put("page", paper.getPage());
            typeDetailCA.put("doi_pmid", paper.getDoi_pmid());
            typeDetailCA.put("paper_type", paper.getPaper_type());
            typeDetailCA.put("ccf_level", paper.getCcf());
            typeDetailCA.put("sci_level", paper.getSci());
            typeDetailCA.put("include", paper.getInclude());
            typeDetailCA.put("publisher", paper.getPublisher());
            typeDetailCA.put("language", paper.getLanguage());
            typeDetailCA.put("is_internation", paper.getIs_internation());
            typeDetailCA.put("is_institution", paper.getIs_institution());
            typeDetailCA.put("remark", paper.getRemark());
            typeDetailCA.put("rule_score", paper.getRule_score());
            typeDetailCA.put("check_score", paper.getCheck_score());
            typeDetailCA.put("rule_accept", paper.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertPatentsToCombinedCA(List<Patent> patents) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Patent patent : patents) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(patent.getSid());
            basicCA.setType("专利发明");
            basicCA.setRule_type(patent.getRule_type());
            basicCA.setRule_detail(patent.getRule_detail());
            basicCA.setUrl(patent.getUrl());
            basicCA.setCheck_status(patent.getCheck_status());
            basicCA.setCheck_msg(patent.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("name", patent.getName());
            typeDetailCA.put("apply_number", patent.getApply_number());
            typeDetailCA.put("certificate_number", patent.getCertificate_number());
            typeDetailCA.put("inventors", patent.getInventors());
            typeDetailCA.put("type", patent.getType());
            typeDetailCA.put("is_accept", patent.is_accept());
            typeDetailCA.put("acc_date", patent.getAcc_date());
            typeDetailCA.put("is_public", patent.is_public());
            typeDetailCA.put("pub_date", patent.getPub_date());
            typeDetailCA.put("is_auth", patent.is_auth());
            typeDetailCA.put("auth_date", patent.getAuth_date());
            typeDetailCA.put("is_transfer", patent.is_transfer());
            typeDetailCA.put("trans_date", patent.getTrans_date());
            typeDetailCA.put("trans_income", patent.getTrans_income());
            typeDetailCA.put("remark", patent.getRemark());
            typeDetailCA.put("rule_score", patent.getRule_score());
            typeDetailCA.put("check_score", patent.getCheck_score());
            typeDetailCA.put("rule_accept", patent.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertExchangeActivitiesToCombinedCA(List<ExchangeActivity> activities) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (ExchangeActivity activity : activities) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(activity.getSid());
            basicCA.setType("赴外校交流");
            basicCA.setRule_type(activity.getRule_type());
            basicCA.setRule_detail(activity.getRule_detail());
            basicCA.setUrl(activity.getUrl());
            basicCA.setCheck_status(activity.getCheck_status());
            basicCA.setCheck_msg(activity.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("name", activity.getName());
            typeDetailCA.put("type", activity.getType());
            typeDetailCA.put("funding", activity.getFunding());
            typeDetailCA.put("dest_country", activity.getDest_country());
            typeDetailCA.put("dest_city", activity.getDest_city());
            typeDetailCA.put("dest_institution", activity.getDest_institution());
            typeDetailCA.put("duration", activity.getDuration());
            typeDetailCA.put("begin", activity.getBegin());
            typeDetailCA.put("end", activity.getEnd());
            typeDetailCA.put("status", activity.getStatus());
            typeDetailCA.put("is_apply", activity.is_apply());
            typeDetailCA.put("is_pre_edu", activity.is_pre_edu());
            typeDetailCA.put("is_signed", activity.is_signed());
            typeDetailCA.put("is_talk", activity.is_talk());
            typeDetailCA.put("remark", activity.getRemark());
            typeDetailCA.put("rule_score", activity.getRule_score());
            typeDetailCA.put("check_score", activity.getCheck_score());
            typeDetailCA.put("rule_accept", activity.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertVolunteersToCombinedCA(List<Volunteer> volunteers) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Volunteer volunteer : volunteers) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(volunteer.getSid());
            basicCA.setType("志愿服务");
            basicCA.setRule_type(volunteer.getRule_type());
            basicCA.setRule_detail(volunteer.getRule_detail());
            basicCA.setUrl(volunteer.getUrl());
            basicCA.setCheck_status(volunteer.getCheck_status());
            basicCA.setCheck_msg(volunteer.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("organization", volunteer.getOrganization());
            typeDetailCA.put("name", volunteer.getName());
            typeDetailCA.put("type", volunteer.getType());
            typeDetailCA.put("begin", volunteer.getBegin());
            typeDetailCA.put("end", volunteer.getEnd());
            typeDetailCA.put("time", volunteer.getTime());
            typeDetailCA.put("rule_score", volunteer.getRule_score());
            typeDetailCA.put("check_score", volunteer.getCheck_score());
            typeDetailCA.put("rule_accept", volunteer.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertSoftwaresToCombinedCA(List<Software> softwares) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Software software : softwares) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(software.getSid());
            basicCA.setType("软件著作权发明");
            basicCA.setRule_type(software.getRule_type());
            basicCA.setRule_detail(software.getRule_detail());
            basicCA.setUrl(software.getUrl());
            basicCA.setCheck_status(software.getCheck_status());
            basicCA.setCheck_msg(software.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("name", software.getName());
            typeDetailCA.put("inv_type", software.getInv_type());
            typeDetailCA.put("inventors", software.getInventors());
            typeDetailCA.put("app_status", software.getApp_status());
            typeDetailCA.put("status_date", software.getStatus_date());
            typeDetailCA.put("remark", software.getRemark());
            typeDetailCA.put("rule_score", software.getRule_score());
            typeDetailCA.put("check_score", software.getCheck_score());
            typeDetailCA.put("rule_accept", software.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertMonographsToCombinedCA(List<Monograph> monographs) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Monograph monograph : monographs) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(monograph.getSid());
            basicCA.setType("专著出版");
            basicCA.setRule_type(monograph.getRule_type());
            basicCA.setRule_detail(monograph.getRule_detail());
            basicCA.setUrl(monograph.getUrl());
            basicCA.setCheck_status(monograph.getCheck_status());
            basicCA.setCheck_msg(monograph.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("title", monograph.getTitle());
            typeDetailCA.put("author_type", monograph.getAuthor_type());
            typeDetailCA.put("authors", monograph.getAuthors());
            typeDetailCA.put("publisher", monograph.getPublisher());
            typeDetailCA.put("category", monograph.getCategory());
            typeDetailCA.put("isbn", monograph.getIsbn());
            typeDetailCA.put("pub_date", monograph.getPub_date());
            typeDetailCA.put("remark", monograph.getRemark());
            typeDetailCA.put("rule_score", monograph.getRule_score());
            typeDetailCA.put("check_score", monograph.getCheck_score());
            typeDetailCA.put("rule_accept", monograph.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertIdeologyMoralitiesToCombinedCA(List<IdeologyMorality> moralities) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (IdeologyMorality morality : moralities) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(morality.getSid());
            basicCA.setType("思想品德");
            basicCA.setRule_type(morality.getRule_type());
            basicCA.setRule_detail(morality.getRule_detail());
            basicCA.setUrl(morality.getUrl());
            basicCA.setCheck_status(morality.getCheck_status());
            basicCA.setCheck_msg(morality.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("attend_time", morality.getAttend_time());
            typeDetailCA.put("commendation", morality.getCommendation());
            typeDetailCA.put("rule_score", morality.getRule_score());
            typeDetailCA.put("check_score", morality.getCheck_score());
            typeDetailCA.put("rule_accept", morality.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertOthersToCombinedCA(List<Others> othersList) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (Others others : othersList) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(others.getSid());
            basicCA.setType("其他");
            basicCA.setRule_type(others.getRule_type());
            basicCA.setRule_detail(others.getRule_detail());
            basicCA.setUrl(others.getUrl());
            basicCA.setCheck_status(others.getCheck_status());
            basicCA.setCheck_msg(others.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("attend_time", others.getAttend_time());
            typeDetailCA.put("discribe", others.getDiscribe());
            typeDetailCA.put("rule_score", others.getRule_score());
            typeDetailCA.put("check_score", others.getCheck_score());
            typeDetailCA.put("rule_accept", others.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

    public static List<CombinedCA> convertSocialWorksToCombinedCA(List<SocialWork> socialWorks) {
        List<CombinedCA> combinedCAs = new ArrayList<>();

        for (SocialWork socialWork : socialWorks) {
            CombinedCA combinedCA = new CombinedCA();
            BasicCA basicCA = new BasicCA();

            basicCA.setSid(socialWork.getSid());
            basicCA.setType("社会工作");
            basicCA.setRule_type(socialWork.getRule_type());
            basicCA.setRule_detail(socialWork.getRule_detail());
            basicCA.setUrl(socialWork.getUrl());
            basicCA.setCheck_status(socialWork.getCheck_status());
            basicCA.setCheck_msg(socialWork.getCheck_msg());

            combinedCA.setBasicInfo(basicCA);

            Map<String, Object> typeDetailCA = new HashMap<>();
            typeDetailCA.put("attend_time", socialWork.getAttend_time());
            typeDetailCA.put("commendation", socialWork.getCommendation());
            typeDetailCA.put("rule_score", socialWork.getRule_score());
            typeDetailCA.put("check_score", socialWork.getCheck_score());
            typeDetailCA.put("rule_accept", socialWork.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

}

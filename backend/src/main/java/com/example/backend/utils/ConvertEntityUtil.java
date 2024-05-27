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
            typeDetailCA.put("提交时间", competition.getSubmit_time());
            typeDetailCA.put("所属学年", competition.getAcademic_year());
            typeDetailCA.put("成果类别", competition.getType());
            typeDetailCA.put("获得时间", competition.getTime());
            typeDetailCA.put("比赛名称", competition.getCompetition_name());
            typeDetailCA.put("获奖名称", competition.getAward_name());
            typeDetailCA.put("成果等级", competition.getResult_level());
            typeDetailCA.put("成果级别", competition.getResult_type());
            typeDetailCA.put("评奖组织单位", competition.getOrganization());
            typeDetailCA.put("其他参与人员", competition.getTeammate());
            typeDetailCA.put("负责人", competition.getCaptain());
            typeDetailCA.put("分数", competition.getRule_score());
            typeDetailCA.put("审核分数", competition.getCheck_score());
            typeDetailCA.put("是否接受", competition.getRule_accept());

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
            typeDetailCA.put("论文标题", paper.getTitle());
            typeDetailCA.put("作者类型", paper.getAuthor_type());
            typeDetailCA.put("全部作者姓名", paper.getAuthors());
            typeDetailCA.put("通讯作者", paper.getCor_author());
            typeDetailCA.put("发表期刊或会议", paper.getJc_name());
            typeDetailCA.put("ISSN/CN", paper.getIssn_cn());
            typeDetailCA.put("影响因子", paper.getImpact_factor());
            typeDetailCA.put("是否有最佳论文奖", paper.is_award());
            typeDetailCA.put("发表状态", paper.getPub_status());
            typeDetailCA.put("投稿日期", paper.getSub_date());
            typeDetailCA.put("接收日期", paper.getAcc_date());
            typeDetailCA.put("发表日期", paper.getPub_date());
            typeDetailCA.put("卷（期）/页码范围", paper.getPage());
            typeDetailCA.put("DOI/PMID", paper.getDoi_pmid());
            typeDetailCA.put("论文类型", paper.getPaper_type());
            typeDetailCA.put("CCF推荐情况", paper.getCcf());
            typeDetailCA.put("中科院分区", paper.getSci());
            typeDetailCA.put("期刊收录情况", paper.getInclude());
            typeDetailCA.put("出版单位", paper.getPublisher());
            typeDetailCA.put("论文语言", paper.getLanguage());
            typeDetailCA.put("是否国际合作论文", paper.getIs_internation());
            typeDetailCA.put("是否本单位合作论文", paper.getIs_institution());
            typeDetailCA.put("备注", paper.getRemark());
            typeDetailCA.put("分数", paper.getRule_score());
            typeDetailCA.put("审核分数", paper.getCheck_score());
            typeDetailCA.put("是否接受", paper.getRule_accept());

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
            typeDetailCA.put("专利名称", patent.getName());
            typeDetailCA.put("专利申请号", patent.getApply_number());
            typeDetailCA.put("专利证书编号", patent.getCertificate_number());
            typeDetailCA.put("全部发明人", patent.getInventors());
            typeDetailCA.put("专利类型", patent.getType());
            typeDetailCA.put("是否已受理", patent.is_accept());
            typeDetailCA.put("受理时间", patent.getAcc_date());
            typeDetailCA.put("是否已公开", patent.is_public());
            typeDetailCA.put("公开时间", patent.getPub_date());
            typeDetailCA.put("是否已授权", patent.is_auth());
            typeDetailCA.put("授权时间", patent.getAuth_date());
            typeDetailCA.put("是否已转让", patent.is_transfer());
            typeDetailCA.put("转让时间", patent.getTrans_date());
            typeDetailCA.put("转让收入", patent.getTrans_income());
            typeDetailCA.put("备注", patent.getRemark());
            typeDetailCA.put("分数", patent.getRule_score());
            typeDetailCA.put("审核分数", patent.getCheck_score());
            typeDetailCA.put("是否接受", patent.getRule_accept());

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
            typeDetailCA.put("项目名称", activity.getName());
            typeDetailCA.put("事由类型", activity.getType());
            typeDetailCA.put("经费来源", activity.getFunding());
            typeDetailCA.put("前往国家", activity.getDest_country());
            typeDetailCA.put("前往城市", activity.getDest_city());
            typeDetailCA.put("前往院校", activity.getDest_institution());
            typeDetailCA.put("项目期限", activity.getDuration());
            typeDetailCA.put("出发时间", activity.getBegin());
            typeDetailCA.put("返校时间", activity.getEnd());
            typeDetailCA.put("目前状态", activity.getStatus());
            typeDetailCA.put("是否申报", activity.is_apply());
            typeDetailCA.put("是否参加行前教育", activity.is_pre_edu());
            typeDetailCA.put("是否已签订", activity.is_signed());
            typeDetailCA.put("是否谈话", activity.is_talk());
            typeDetailCA.put("备注", activity.getRemark());
            typeDetailCA.put("分数", activity.getRule_score());
            typeDetailCA.put("审核分数", activity.getCheck_score());
            typeDetailCA.put("是否接受", activity.getRule_accept());

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
            typeDetailCA.put("志愿组织团体（全称）", volunteer.getOrganization());
            typeDetailCA.put("志愿活动名称（全称）", volunteer.getName());
            typeDetailCA.put("志愿活动类型", volunteer.getType());
            typeDetailCA.put("参与志愿活动开始时间", volunteer.getBegin());
            typeDetailCA.put("参与志愿活动结束时间", volunteer.getEnd());
            typeDetailCA.put("志愿服务时长（X小时）", volunteer.getTime());
            typeDetailCA.put("分数", volunteer.getRule_score());
            typeDetailCA.put("审核分数", volunteer.getCheck_score());
            typeDetailCA.put("是否接受", volunteer.getRule_accept());

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
            typeDetailCA.put("软件著作权名称", software.getName());
            typeDetailCA.put("第几发明人", software.getInv_type());
            typeDetailCA.put("全部完成人", software.getInventors());
            typeDetailCA.put("申请状态", software.getApp_status());
            typeDetailCA.put("状态时间", software.getStatus_date());
            typeDetailCA.put("备注", software.getRemark());
            typeDetailCA.put("分数", software.getRule_score());
            typeDetailCA.put("审核分数", software.getCheck_score());
            typeDetailCA.put("是否接受", software.getRule_accept());

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
            typeDetailCA.put("专著名称", monograph.getTitle());
            typeDetailCA.put("第几作者", monograph.getAuthor_type());
            typeDetailCA.put("该著作全部作者", monograph.getAuthors());
            typeDetailCA.put("出版单位", monograph.getPublisher());
            typeDetailCA.put("著作类别", monograph.getCategory());
            typeDetailCA.put("ISBN", monograph.getIsbn());
            typeDetailCA.put("出版时间", monograph.getPub_date());
            typeDetailCA.put("备注", monograph.getRemark());
            typeDetailCA.put("分数", monograph.getRule_score());
            typeDetailCA.put("审核分数", monograph.getCheck_score());
            typeDetailCA.put("是否接受", monograph.getRule_accept());

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
            typeDetailCA.put("参与时间", morality.getAttend_time());
            typeDetailCA.put("获得表彰", morality.getCommendation());
            typeDetailCA.put("分数", morality.getRule_score());
            typeDetailCA.put("审核分数", morality.getCheck_score());
            typeDetailCA.put("是否接受", morality.getRule_accept());

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
            typeDetailCA.put("参与时间", others.getAttend_time());
            typeDetailCA.put("具体描述", others.getDiscribe());
            typeDetailCA.put("分数", others.getRule_score());
            typeDetailCA.put("审核分数", others.getCheck_score());
            typeDetailCA.put("是否接受", others.getRule_accept());

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
            typeDetailCA.put("参与时间", socialWork.getAttend_time());
            typeDetailCA.put("获得表彰", socialWork.getCommendation());
            typeDetailCA.put("分数", socialWork.getRule_score());
            typeDetailCA.put("审核分数", socialWork.getCheck_score());
            typeDetailCA.put("是否接受", socialWork.getRule_accept());

            combinedCA.setDetailCA(typeDetailCA);

            combinedCAs.add(combinedCA);
        }

        return combinedCAs;
    }

}

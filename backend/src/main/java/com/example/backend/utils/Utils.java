package com.example.backend.utils;

import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class Utils {
    private final CompetitionRepository comp;
    private final PaperRepository paper;
    private final PatentRepository patent;
    private final SoftwareRepository software;
    private final MonographRepository monograph;
    private final ExchangeActivityRepository activity;

    private final VolunteerRepository volunteer;

    @Autowired
    public Utils(CompetitionRepository comp,
                 PaperRepository paper,
                 PatentRepository patent,
                 SoftwareRepository software,
                 MonographRepository monograph,
                 ExchangeActivityRepository activity,
                 VolunteerRepository volunteer) {
        this.comp = comp;
        this.paper = paper;
        this.patent = patent;
        this.software = software;
        this.monograph = monograph;
        this.activity = activity;
        this.volunteer = volunteer;
    }

    public List<Map<String, Object>> getInfoFromTable(String sid, Date begin, Date end){
        List<Map<String, Object>> lc = new ArrayList<>();
        List<Map<String, Object>> l = comp.getStuCompFromTime(sid, begin, end);
        for(Map<String, Object> m:l){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","比赛获奖");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score")||k.equals("id")||k.equals("check_score")||k.equals("rule_accept") ){

                    new_map.put(k, v);
                    continue;
                }
                if(k.equals("competition_name")){
                    new_map.put("name",v);
                    continue;
                }

                String cname = getCompChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }

        List<Map<String, Object>> l1 = paper.getStuPaperFromTime(sid, begin, end);
        for(Map<String, Object> m:l1){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","论文发表");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score")||k.equals("id")||k.equals("check_score") ||k.equals("rule_accept")){

                    new_map.put(k, v);
                    continue;
                }
                if(k.equals("title")){
                    new_map.put("name",v);
                    continue;
                }

                String cname = getPaperChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }
        List<Map<String, Object>> l2 = patent.getStuPatentFromTime(sid, begin, end);
        for(Map<String, Object> m:l2){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","专利发明");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score") || k.equals("name")||k.equals("id")||k.equals("check_score")||k.equals("rule_accept") ){

                    new_map.put(k, v);
                    continue;
                }

                String cname = getPatentChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }

        List<Map<String, Object>> l3 = software.getStuSoftwareFromTime(sid, begin, end);
        for(Map<String, Object> m:l3){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","软件著作权发明");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score") || k.equals("name")||k.equals("id") ||k.equals("check_score")||k.equals("rule_accept")){

                    new_map.put(k, v);
                    continue;
                }

                String cname = getSoftwareChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }
        List<Map<String, Object>> l4 = monograph.getStuMonoFromTime(sid, begin, end);
        for(Map<String, Object> m:l4){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","专著出版");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score") ||k.equals("id") ||k.equals("check_score")||k.equals("rule_accept")){

                    new_map.put(k, v);
                    continue;
                }
                if (k.equals("title")){
                    new_map.put("name",v);
                    continue;
                }

                String cname = getMonographChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }

        List<Map<String, Object>> l5 = activity.getStuActivityFromTime(sid, begin, end);
        for(Map<String, Object> m:l5){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","赴外校交流");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score") || k.equals("name")||k.equals("id")||k.equals("check_score")||k.equals("rule_accept") ){

                    new_map.put(k, v);
                    continue;
                }

                String cname = getActivityChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }

        List<Map<String, Object>> l6 = volunteer.getStuVolunteerFromTime(sid, begin, end);
        for(Map<String, Object> m:l6){
            Map<String, Object> new_map = new HashMap<>();
            new_map.put("source","志愿服务");
            StringBuilder str = new StringBuilder();

            for (Map.Entry<String, Object> entry : m.entrySet()){
                String k = entry.getKey();
                Object v = entry.getValue();

                if(k.equals("rule_type") || k.equals("rule_detail")
                        || k.equals("rule_score") || k.equals("name")||k.equals("id") ||k.equals("check_score")||k.equals("rule_accept")){

                    new_map.put(k, v);
                    continue;
                }

                String cname = getVolunteerChineseName(k); // 根据字段名获取字段的中文名，需要根据实际情况实现
                String value = v != null ? v.toString() : ""; // 将字段值转为字符串，如果字段值为null，则赋为空字符串

                str.append(cname).append(":").append(value).append(", ");
            }

            String s = str.toString();
            s = s.substring(0, s.length()-1);

            // 其余字段的key为others, value为合成的string
            new_map.put("others", s);
            lc.add(new_map);
        }
        return lc;

    }

    @Transactional
    public void updateCheckScoreByTableName(String name, int id, double score){
        if(Objects.equals(name, "比赛获奖")){
            comp.updateCheckScoreById(id, score);
        } else if (Objects.equals(name, "论文发表")) {
            paper.updateCheckScoreById(id, score);
        } else if (Objects.equals(name, "专利发明")) {
            patent.updateCheckScoreById(id, score);
        }else if (Objects.equals(name, "软件著作权发明")) {
            software.updateCheckScoreById(id, score);
        }else if (Objects.equals(name, "专著出版")) {
            monograph.updateCheckScoreById(id, score);
        }else if (Objects.equals(name, "赴外校交流")) {
            activity.updateCheckScoreById(id, score);
        }else if (Objects.equals(name, "志愿服务")) {
            volunteer.updateCheckScoreById(id, score);
        }
    }

    @Transactional
    public void updateRuleAcceptByTableName(String name, int id, int flag){
        if(Objects.equals(name, "比赛获奖")){
            comp.updateRuleAcceptById(id, flag);
        } else if (Objects.equals(name, "论文发表")) {
            paper.updateRuleAcceptById(id, flag);
        }else if (Objects.equals(name, "专利发明")) {
            patent.updateRuleAcceptById(id, flag);
        }else if (Objects.equals(name, "软件著作权发明")) {
            software.updateRuleAcceptById(id, flag);
        }else if (Objects.equals(name, "专著出版")) {
            monograph.updateRuleAcceptById(id, flag);
        }else if (Objects.equals(name, "赴外校交流")) {
            activity.updateRuleAcceptById(id, flag);
        }else if (Objects.equals(name, "志愿服务")) {
            volunteer.updateRuleAcceptById(id, flag);
        }
    }


    private String getCompChineseName(String fieldName) {
        // 根据字段名返回相应的字段的中文名
        return switch (fieldName) {
            case "id" -> "编号";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "submit_time" -> "提交时间";
            case "academic_year" -> "所属学年";
            case "sid" -> "学生学号";
            case "type" -> "成果类别";
            case "time" -> "获得时间";
            case "competition_name" -> "比赛名称";
            case "award_name" -> "获奖名称";
            case "result_level" -> "成果等级";
            case "result_type" -> "成果级别";
            case "organization" -> "评奖组织单位";
            case "teammate" -> "其他参与人员";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            case "captain" -> "负责人";
            case "name" -> "姓名";
            default -> "";
        };
    }

    private String getStuChineseName(String name){
        return switch (name){
            case "id" -> "编号";
            case "netId" -> "netID";
            case "password" -> "密码";
            case "studentRole" -> "学生身份";
            case "isUndergraduate" -> "是否本科生";
            case "admissionYear" -> "入学年份";
            case "classId" -> "班级";
            case "name" -> "姓名";
            case "type" -> "类型";
            case "nation" -> "民族";
            case "sid" -> "学号";
            case "pid" -> "身份证号";
            case "birth" -> "出生年月";
            case "politics" -> "政治面貌";
            case "dormitory" -> "宿舍";
            case "nativePlace" -> "籍贯";
            case "phone" -> "电话";
            case "address" -> "地址";
            case "urgentPhone" -> "紧急联系电话";
            case "urgentName" -> "紧急联系人姓名";
            case "wechat" -> "微信";
            case "email" -> "电子邮件";
            default -> "";
        };
    }

    private String getPaperChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "title" -> "论文标题";
            case "author_type" -> "作者类型";
            case "authors" -> "全部作者姓名";
            case "cor_author" -> "通讯作者";
            case "jc_name" -> "发表期刊或会议";
            case "issn_cn" -> "ISSN/CN";
            case "impact_factor" -> "影响因子";
            case "is_award" -> "是否有最佳论文奖";
            case "pub_status" -> "发表状态";
            case "sub_date" -> "投稿日期";
            case "acc_date" -> "接收日期";
            case "pub_date" -> "发表日期";
            case "page" -> "卷（期）/页码范围";
            case "doi_pmid" -> "DOI/PMID";
            case "paper_type" -> "论文类型";
            case "ccf_level" -> "CCF推荐情况";
            case "sci_level" -> "中科院分区";
            case "include" -> "期刊收录情况";
            case "publisher" -> "出版单位";
            case "language" -> "论文语言";
            case "is_internation" -> "是否国际合作论文";
            case "is_institution" -> "是否本单位合作论文";
            case "remark" -> "备注";
            case "sid" -> "学生学号";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            default -> "";
        };
    }

    public String getPatentChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "name" -> "专利名称";
            case "apply_number" -> "专利申请号";
            case "certificate_number" -> "专利证书编号";
            case "inventors" -> "全部发明人";
            case "type" -> "专利类型";
            case "is_accept" -> "是否已受理";
            case "acc_date" -> "受理时间";
            case "is_public" -> "是否已公开";
            case "pub_date" -> "公开时间";
            case "is_auth" -> "是否已授权";
            case "auth_date" -> "授权时间";
            case "is_transfer" -> "是否已转让";
            case "trans_date" -> "转让时间";
            case "trans_income" -> "转让收入";
            case "remark" -> "备注";
            case "sid" -> "学生学号";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            default -> "";
        };
    }

    public String getSoftwareChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "name" -> "软件著作权名称";
            case "inv_type" -> "第几发明人";
            case "inventors" -> "全部完成人";
            case "app_status" -> "申请状态";
            case "status_date" -> "状态时间";
            case "remark" -> "备注";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "sid" -> "学生学号";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            default -> "";
        };
    }

    public String getMonographChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "title" -> "专著名称";
            case "author_type" -> "第几作者";
            case "authors" -> "该著作全部作者";
            case "publisher" -> "出版单位";
            case "category" -> "著作类别";
            case "isbn" -> "ISBN";
            case "pub_date" -> "出版时间";
            case "remark" -> "备注";
            case "sid" -> "学生学号";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            default -> "";
        };
    }

    public String getVolunteerChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "sid" -> "学生学号";
            case "organization" -> "志愿组织团体（全称）";
            case "name" -> "志愿活动名称（全称）";
            case "type" -> "志愿活动类型";
            case "begin" -> "参与志愿活动开始时间";
            case "end" -> "参与志愿活动结束时间";
            case "time" -> "志愿服务时长（X小时）";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "综测细则";
            case "rule_score" -> "分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否接受";
            default -> "";
        };
    }

    public String getActivityChineseName(String fieldName) {
        return switch (fieldName) {
            case "id" -> "编号";
            case "name" -> "项目名称";
            case "type" -> "事由类型";
            case "funding" -> "经费来源";
            case "dest_country" -> "前往国家";
            case "dest_city" -> "前往城市";
            case "dest_institution" -> "前往院校";
            case "duration" -> "项目期限";
            case "begin" -> "出发时间";
            case "end" -> "返校时间";
            case "status" -> "目前状态";
            case "is_apply" -> "是否申报";
            case "is_pre_edu" -> "是否参加行前教育";
            case "is_signed" -> "是否已签订";
            case "is_talk" -> "是否谈话";
            case "remark" -> "备注";
            case "sid" -> "学生学号";
            case "url" -> "材料链接";
            case "check_status" -> "审核状态";
            case "check_msg" -> "审核意见";
            case "rule_type" -> "综测大类";
            case "rule_detail" -> "细则";
            case "rule_score" -> "细则分数";
            case "check_score" -> "审核分数";
            case "rule_accept" -> "是否加入综测";
            default -> "";
        };
    }
}

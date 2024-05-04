package com.example.backend.utils;

import com.example.backend.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class Utils {
    private final CompetitionRepository repository;
    public List<Map<String, Object>> getInfoFromTable(String name, String sid, Date begin, Date end){
        List<Map<String, Object>> lc = new ArrayList<>();
        if(Objects.equals(name, "比赛")){
            List<Map<String, Object>> l = repository.getStuCompFromTime(sid, begin, end);
            for(Map<String, Object> m:l){
                Map<String, Object> new_map = new HashMap<>();
                StringBuilder str = new StringBuilder();

                for (Map.Entry<String, Object> entry : m.entrySet()){
                    String k = entry.getKey();
                    Object v = entry.getValue();

                    if(k.equals("rule_type") || k.equals("rule_detail")
                            || k.equals("rule_score") || k.equals("competition_name")){

                        new_map.put(k, v);
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
            return lc;
        }

        return null;

    }


    public void updateCheckScoreFromTable(String name, int id, float score){
        if(Objects.equals(name, "比赛")){
            repository.updateCheckScoreById(id, score);
        } else if (Objects.equals(name, "论文")) {
            return ;
        }
    }

    public void updateRuleAcceptFromTable(String name, int id, int flag){
        if(Objects.equals(name, "比赛")){
            repository.updateRuleAcceptById(id, flag);
        } else if (Objects.equals(name, "论文")) {
            return ;
        }
    }

    private static String getCompChineseName(String fieldName) {
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
            default -> "";
        };

    }
}

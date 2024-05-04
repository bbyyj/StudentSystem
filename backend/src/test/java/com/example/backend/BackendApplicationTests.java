package com.example.backend;

import com.example.backend.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {
	private static Utils u;

	@Autowired
	public BackendApplicationTests(Utils utils) {
		u = utils;
	}

	@Test
	void contextLoads() {
		List<Map<String, Object>> l = u.getInfoFromTable("比赛", "21310001", Date.valueOf("2018-09-01"), Date.valueOf("2024-01-01"));
		for (Map<String, Object> map : l) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				System.out.println(key + ": " + value);
			}
		}

		u.updateCheckScoreByTableName("比赛", 1, 0.8);
		u.updateRuleAcceptByTableName("比赛", 2, 1);
	}

}

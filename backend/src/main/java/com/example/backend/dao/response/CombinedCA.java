package com.example.backend.dao.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CombinedCA {
    private BasicCA basicInfo;
    private Map<String, Object> DetailCA;

}


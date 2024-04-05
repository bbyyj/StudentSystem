package com.example.backend.dao.response;

import com.example.backend.entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminInformationResponse {
    private Admin admin;
}

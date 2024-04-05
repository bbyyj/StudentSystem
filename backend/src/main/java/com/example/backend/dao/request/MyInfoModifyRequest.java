package com.example.backend.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoModifyRequest {
    private String netId;
    private String phone;
    private String address;
    private String urgentPhone;
    private String urgentName;
    private String wechat;
    private String email;
}



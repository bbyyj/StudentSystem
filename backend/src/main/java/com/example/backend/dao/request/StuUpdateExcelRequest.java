package com.example.backend.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StuUpdateExcelRequest {
    private String sid;
    private String dormitory;
    private String name;
    private String type;
    private String nation;
    private String pid;
    private String birth;
    private String politics;
    private String nativePlace;

}

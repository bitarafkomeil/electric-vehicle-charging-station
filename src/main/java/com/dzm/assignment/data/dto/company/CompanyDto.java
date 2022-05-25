package com.dzm.assignment.data.dto.company;

import lombok.Data;

@Data
public class CompanyDto extends BaseCompanyDto {
    private Long id;
    private String parentName;
}
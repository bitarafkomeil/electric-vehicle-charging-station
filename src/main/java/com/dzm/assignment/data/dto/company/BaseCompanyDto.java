package com.dzm.assignment.data.dto.company;

import lombok.Data;

@Data
public class BaseCompanyDto {
    private String name;
    private Long parentId;
}
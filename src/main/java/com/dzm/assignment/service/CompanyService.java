package com.dzm.assignment.service;

import com.dzm.assignment.data.dto.company.CompanyDto;
import com.dzm.assignment.data.dto.company.CreateCompanyDto;
import com.dzm.assignment.data.dto.company.UpdateCompanyDto;
import com.dzm.assignment.data.model.Company;
import com.dzm.assignment.data.repository.CompanyRepository;
import com.dzm.assignment.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends BaseService<Company, CompanyRepository,
        CreateCompanyDto, UpdateCompanyDto, CompanyDto, CompanyMapper> {
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        super(companyRepository, companyMapper);
    }
}
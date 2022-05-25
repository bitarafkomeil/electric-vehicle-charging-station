package com.dzm.assignment.service;

import com.dzm.assignment.data.dto.company.CompanyDto;
import com.dzm.assignment.data.dto.company.CreateCompanyDto;
import com.dzm.assignment.data.dto.company.UpdateCompanyDto;
import com.dzm.assignment.data.model.Company;
import com.dzm.assignment.data.repository.CompanyRepository;
import com.dzm.assignment.exception.NotFoundException;
import com.dzm.assignment.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService extends BaseService<Company, CompanyRepository,
        CreateCompanyDto, UpdateCompanyDto, CompanyDto, CompanyMapper> {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        super(companyRepository, companyMapper);
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }
    public List<CompanyDto> findAllWithChild(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company Id InCorrect"));
        List<Company> result = loadAllCompanyChild(company, new ArrayList<>());
        return companyMapper.toDto(result);
    }
}
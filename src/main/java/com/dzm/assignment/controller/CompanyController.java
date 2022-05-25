package com.dzm.assignment.controller;


import com.dzm.assignment.data.dto.company.CompanyDto;
import com.dzm.assignment.data.dto.company.CreateCompanyDto;
import com.dzm.assignment.data.dto.company.UpdateCompanyDto;
import com.dzm.assignment.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/companies")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CreateCompanyDto createCompanyDTO) {
        CompanyDto companyDto = companyService.create(createCompanyDTO);
        return ResponseEntity.ok().body(companyDto);
    }

    @PutMapping()
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody UpdateCompanyDto updateCompanyDTO) {
        CompanyDto companyDto = companyService.update(updateCompanyDTO);
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) {
        CompanyDto companyDto = companyService.findOne(id);
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping()
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(Pageable pageable) {
        Page<CompanyDto> companies = companyService.findAll(pageable);
        return ResponseEntity.ok()
                .body(companies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
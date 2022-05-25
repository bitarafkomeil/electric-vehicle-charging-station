package com.dzm.assignment.mapper;

import com.dzm.assignment.data.dto.company.CompanyDto;
import com.dzm.assignment.data.dto.company.CreateCompanyDto;
import com.dzm.assignment.data.dto.company.UpdateCompanyDto;
import com.dzm.assignment.data.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Mapper for {@link Company} and its DTOs.
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<CreateCompanyDto, UpdateCompanyDto, CompanyDto, Company> {

    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "parentId", target = "parent", qualifiedByName = "checkParent")
    @Mapping(target = "id", ignore = true)
    Company fromCreateDTO(CreateCompanyDto createCompanyDto);

    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "parentId", target = "parent", qualifiedByName = "checkParent")
    Company fromUpdateDTO(UpdateCompanyDto dto);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    CompanyDto toDto(Company entity);

    List<CompanyDto> toDto(List<Company> entityList);

    @Named("checkParent")
    default Company checkParent(Long parentId) {
        if (parentId == null)
            return null;
        else {
            Company parent = new Company();
            parent.setId(parentId);
            return parent;
        }
    }
}
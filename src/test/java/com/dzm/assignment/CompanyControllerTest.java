package com.dzm.assignment;

import com.dzm.assignment.data.dto.company.CompanyDto;
import com.dzm.assignment.data.dto.company.CreateCompanyDto;
import com.dzm.assignment.data.dto.company.UpdateCompanyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    static CompanyDto companyDto;
    static CompanyDto companyDtoForDelete;


    @Test
    @Order(1)
    public void createCompany() throws Exception {
        CreateCompanyDto createCompanyDto = new CreateCompanyDto();
        createCompanyDto.setParentId(null);
        createCompanyDto.setName("test");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/companies")
                .content(asJsonString(createCompanyDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        companyDto = objectMapper.readValue(contentAsString, CompanyDto.class);
    }

    @Test
    @Order(2)
    public void createCompany2() throws Exception {
        CreateCompanyDto createCompanyDto = new CreateCompanyDto();
        createCompanyDto.setParentId(null);
        createCompanyDto.setName("test2");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/companies")
                .content(asJsonString(createCompanyDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        companyDtoForDelete = objectMapper.readValue(contentAsString, CompanyDto.class);
    }

    @Test
    @Order(3)
    public void updateCompany() throws Exception {
        UpdateCompanyDto updateCompanyDto = new UpdateCompanyDto();
        updateCompanyDto.setParentId(null);
        updateCompanyDto.setId(companyDto.getId());
        updateCompanyDto.setName("updated");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/companies")
                .content(asJsonString(updateCompanyDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        companyDto = objectMapper.readValue(contentAsString, CompanyDto.class);
    }

    @Test
    @Order(4)
    public void getCompany() throws Exception {
        mockMvc.perform(get("/api/companies/{id}", companyDto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    public void getAllCompanies() throws Exception {
        mockMvc.perform(get("/api/companies/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void getAllCompaniesWithChild() throws Exception {
        mockMvc.perform(get("/api/companies/{id}/child/", companyDto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(7)
    public void deleteCompany() throws Exception {
        mockMvc.perform(get("/api/companies/{id}", companyDtoForDelete.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

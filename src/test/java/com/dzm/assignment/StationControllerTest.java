package com.dzm.assignment;

import com.dzm.assignment.data.dto.station.CreateStationDto;
import com.dzm.assignment.data.dto.station.StationDto;
import com.dzm.assignment.data.dto.station.UpdateStationDto;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static StationDto stationDto;


    @Test
    @Order(1)
    public void createStation() throws Exception {
        CreateStationDto createStationDto = new CreateStationDto();
        createStationDto.setLatitude(1d);
        createStationDto.setLongitude(1d);
        createStationDto.setCompanyId(CompanyControllerTest.companyDto.getId());
        createStationDto.setName("test");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/stations")
                .content(asJsonString(createStationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        stationDto = objectMapper.readValue(contentAsString, StationDto.class);
    }

    @Test
    @Order(2)
    public void updateStation() throws Exception {
        UpdateStationDto updateStationDto = new UpdateStationDto();
        updateStationDto.setId(stationDto.getId());
        updateStationDto.setLatitude(stationDto.getLatitude());
        updateStationDto.setLongitude(stationDto.getLongitude());
        updateStationDto.setCompanyId(stationDto.getCompanyId());
        updateStationDto.setName("updated");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/stations")
                .content(asJsonString(updateStationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        stationDto = objectMapper.readValue(contentAsString, StationDto.class);
    }

    @Test
    @Order(3)
    public void getStation() throws Exception {
        mockMvc.perform(get("/api/stations/{id}", stationDto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(4)
    public void getAllStations() throws Exception {
        mockMvc.perform(get("/api/stations/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    public void getAllNearestStations() throws Exception {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("companyId", stationDto.getCompanyId().toString());
        paramMap.add("latitude", String.valueOf(2d));
        paramMap.add("longitude", String.valueOf(3d));
        paramMap.add("radius", String.valueOf(1000d));
        mockMvc.perform(get("/api/stations/nearest")
                .params(paramMap))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void deleteStation() throws Exception {
        mockMvc.perform(get("/api/companies/{id}", stationDto.getId()))
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

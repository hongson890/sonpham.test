package com.oddle.app.test.service;

import com.oddle.app.config.AppConfig;
import com.oddle.app.config.HibernateConfiguration;
import com.oddle.app.gateway.OpenWeatherMapGateway;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.service.WeatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(classes = {AppConfig.class, HibernateConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class WeatherLogServiceTest {

    private MockRestServiceServer mockServer;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherService weatherService;

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    @Rollback
    public void shouldAbleToQueryData() {

        String mockTest = "{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":284.24,\"feels_like\":279.97,\"temp_min\":283.71,\"temp_max\":284.82,\"pressure\":1000,\"humidity\":93},\"visibility\":10000,\"wind\":{\"speed\":6.17,\"deg\":250},\"clouds\":{\"all\":90},\"dt\":1611823170,\"sys\":{\"type\":1,\"id\":1414,\"country\":\"GB\",\"sunrise\":1611819874,\"sunset\":1611852130},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}";
        mockServer.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=test&appid=c8f9f84d1ebf3d91bb1d3cd449edbd27"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON).body(mockTest));

        ResponseEntity<OpenWeatherMapInfoDTO> result = weatherService.searchByParams("test");
        Assert.assertTrue(result.getBody().getName().equals("London"));
    }

    @Test
    @Transactional
    @Rollback
    public void shouldAbleToHandleIfNoDataFound() {

        String mockTest = "{\"cod\":\"404\",\"message\":\"city not found\"}";
        mockServer.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=test&appid=c8f9f84d1ebf3d91bb1d3cd449edbd27"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON).body(mockTest));

        ResponseEntity<OpenWeatherMapInfoDTO> result = weatherService.searchByParams("test");
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

    }

}

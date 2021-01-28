package com.oddle.app.gateway.impl;

import com.oddle.app.gateway.OpenWeatherMapGateway;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.service.impl.WeatherServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OpenWeatherMapGatewayImpl implements OpenWeatherMapGateway {

    private static final Logger logger = Logger.getLogger(OpenWeatherMapGatewayImpl.class);

    private final String openWeatherMapEndpoint;

    private final String apiKey;

    private final RestTemplate restTemplate;


    @Autowired
    public OpenWeatherMapGatewayImpl(@Value("${openweathermap.api.url}") final String openWeatherMapEndpoint,
                                     @Value("${openweathermap.api.key}") final String apiKey,
                                     final RestTemplate restTemplate) {
        this.openWeatherMapEndpoint = openWeatherMapEndpoint;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public OpenWeatherMapInfoDTO getWeatherData(String inputParam) {
        logger.info("getWeatherData inputParam = " + inputParam);
        URI uri = UriComponentsBuilder
                .fromHttpUrl(openWeatherMapEndpoint)
                .queryParam("q", inputParam)
                .queryParam("appid", apiKey)
                .build().toUri();
        return restTemplate.getForObject(uri, OpenWeatherMapInfoDTO.class);
    }
}

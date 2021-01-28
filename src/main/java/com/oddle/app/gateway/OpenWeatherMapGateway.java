package com.oddle.app.gateway;

import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;

public interface OpenWeatherMapGateway {

    OpenWeatherMapInfoDTO getWeatherData(String inputParam);
}

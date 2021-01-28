package com.oddle.app.service;

import com.oddle.app.api.dto.WeatherLogDTO;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.model.WeatherLog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WeatherService {
    ResponseEntity<OpenWeatherMapInfoDTO> searchByParams(String inputParam);
    ResponseEntity<Void> deleteWeatherLog(Long weatherLogId);
    ResponseEntity<List<WeatherLogDTO>> getListWeatherLog();
}

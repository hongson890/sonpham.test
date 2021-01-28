package com.oddle.app.api.endpoint;

import com.oddle.app.api.WeatherResource;
import com.oddle.app.api.dto.WeatherLogDTO;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.model.WeatherLog;
import com.oddle.app.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@Api("Weather endpoint handling operations.")
public class WeatherResourceImpl implements WeatherResource {


    private static final Logger logger = Logger.getLogger(WeatherResourceImpl.class);

    private final WeatherService weatherService;

    @Autowired
    public WeatherResourceImpl(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    @GetMapping("/searchByParam")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("search weather from city by using third party endpoint")
    public ResponseEntity<OpenWeatherMapInfoDTO> searchByParams(@RequestParam(value = "inputParam", defaultValue = "") String inputParam) {
        return weatherService.searchByParams(inputParam);
    }

    @Override
    @GetMapping("/getListWeatherLog")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("get list of weather log from database")
    public ResponseEntity<List<WeatherLogDTO>> getListWeatherLog() {
        logger.info("Start getListWeatherLog ");
        return weatherService.getListWeatherLog();
    }

    @Override
    @DeleteMapping("/weather-log/{weatherLogId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("delete weather_log by id")
    public ResponseEntity<Void> deleteWeatherLog(@PathVariable Long weatherLogId) {
        return weatherService.deleteWeatherLog(weatherLogId);
    }


}

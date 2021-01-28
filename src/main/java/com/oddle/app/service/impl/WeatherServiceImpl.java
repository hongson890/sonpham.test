package com.oddle.app.service.impl;

import com.oddle.app.api.dto.WeatherLogDTO;
import com.oddle.app.gateway.OpenWeatherMapGateway;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.mapper.WeatherMapper;
import com.oddle.app.model.WeatherLog;
import com.oddle.app.repository.WeatherLogDAO;
import com.oddle.app.service.WeatherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = Logger.getLogger(WeatherServiceImpl.class);

    private final OpenWeatherMapGateway openWeatherMapGateway;

    private final WeatherLogDAO weatherLogDAO;

    @Autowired
    public WeatherServiceImpl(final OpenWeatherMapGateway openWeatherMapGateway, WeatherLogDAO weatherLogDAO) {
        this.openWeatherMapGateway = openWeatherMapGateway;
        this.weatherLogDAO = weatherLogDAO;
    }

    @Override
    public ResponseEntity<OpenWeatherMapInfoDTO> searchByParams(String inputParam) {
        logger.info("START searchByParams inputParam {} " + inputParam);
        try {
            OpenWeatherMapInfoDTO openWeatherMapInfoDTO = openWeatherMapGateway.getWeatherData(inputParam);
            // if city not found
            if (openWeatherMapInfoDTO.getCod() == 404) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            // save to log data
            logger.info("save to log data {} " + openWeatherMapInfoDTO.toString());
            WeatherLog weatherLog = WeatherMapper.toWeatherLog(openWeatherMapInfoDTO);
            weatherLog.setUpdatedOn(new Date());
            weatherLogDAO.save(weatherLog);


            return new ResponseEntity<>(openWeatherMapInfoDTO, HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            logger.warn("ex: {}", ex);
            return new ResponseEntity<>(null, ex.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteWeatherLog(Long weatherLogId) {
        logger.info("START deleteWeatherLog weatherLogId {} " + weatherLogId);
        WeatherLog weatherLog = weatherLogDAO.findById(weatherLogId);
        if (weatherLog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                weatherLogDAO.delete(weatherLog);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public ResponseEntity<List<WeatherLogDTO>> getListWeatherLog() {
        List<WeatherLogDTO> weatherLogList = weatherLogDAO.findAll().stream()
                .sorted(Comparator.comparing(WeatherLog::getUpdatedOn).reversed())
                .map(WeatherMapper::toWeatherLogDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(weatherLogList, HttpStatus.OK);
    }
}

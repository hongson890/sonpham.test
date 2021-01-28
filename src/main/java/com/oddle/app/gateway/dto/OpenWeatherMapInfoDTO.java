package com.oddle.app.gateway.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OpenWeatherMapInfoDTO {
    private CoordDTO coord;
    private Set<WeatherDTO> weather;
    private String base;
    private MainDTO main;
    private long visibility;
    private WindDTO wind;
    private CloudsDTO clouds;
    private long dt;
    private SysDTO sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}

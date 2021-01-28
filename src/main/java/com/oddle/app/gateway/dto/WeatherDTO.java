package com.oddle.app.gateway.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    private long id;
    private String main;
    private String description;
    private String icon;
}

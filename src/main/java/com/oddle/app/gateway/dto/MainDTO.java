package com.oddle.app.gateway.dto;

import lombok.Data;

@Data
public class MainDTO {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;
}

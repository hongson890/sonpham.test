package com.oddle.app.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherLogDTO {

    private Long id;
    private String city;
    private String country;
    private Date updatedOn;
    private Double temperature;
    private Double windSpeed;
    private Double humidity;
    private List<OWMDetailWeatherDTO> weather;

}

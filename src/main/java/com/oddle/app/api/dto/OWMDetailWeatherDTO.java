package com.oddle.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OWMDetailWeatherDTO {
    private String main;
    private String description;
    private String icon;
}

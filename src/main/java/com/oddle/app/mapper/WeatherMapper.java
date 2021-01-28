package com.oddle.app.mapper;

import com.oddle.app.api.dto.OWMDetailWeatherDTO;
import com.oddle.app.api.dto.WeatherLogDTO;
import com.oddle.app.common.WeatherProvider;
import com.oddle.app.gateway.dto.OpenWeatherMapInfoDTO;
import com.oddle.app.gateway.dto.WeatherDTO;
import com.oddle.app.model.OWMDetail;
import com.oddle.app.model.OWMDetailWeather;
import com.oddle.app.model.WeatherLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherMapper {

    public static WeatherLogDTO toWeatherLogDTO(WeatherLog x) {
        return WeatherLogDTO.builder()
                .id(x.getId())
                .city(x.getOWMDetail().getName())
                .country(x.getOWMDetail().getSysCountry())
                .weather(x.getOWMDetail().getWeather().stream().map(WeatherMapper::toDTO).collect(Collectors.toList()))
                .temperature(x.getOWMDetail().getMainTemp())
                .windSpeed(x.getOWMDetail().getWindSpeed())
                .humidity(x.getOWMDetail().getMainHumidity())
                .updatedOn(x.getUpdatedOn())
                .build();
    }

    public static OWMDetailWeatherDTO toDTO(OWMDetailWeather x) {
        return OWMDetailWeatherDTO.builder()
                .description(x.getDescription())
                .icon(x.getIcon())
                .main(x.getMain())
                .build();
    }

    public static WeatherLog toWeatherLog(OpenWeatherMapInfoDTO x) {
        return WeatherLog.builder()
                .weatherProvider(WeatherProvider.OPEN_WEATHER_MAP)
                .OWMDetail(WeatherMapper.toOWMDetail(x))
                .build();
    }

    public static OWMDetailWeather toOWMDetailWeather(WeatherDTO x) {
        return OWMDetailWeather.builder()
                .description(x.getDescription())
                .icon(x.getIcon())
                .main(x.getMain())
                .build();
    }

    public static OWMDetail toOWMDetail(OpenWeatherMapInfoDTO x) {
        OWMDetail owmDetail = OWMDetail.builder()
                .base(x.getBase())
                .cod(x.getCod())
                .cloudsAll(x.getClouds().getAll())
                .coordLat(x.getCoord().getLat())
                .coordLon(x.getCoord().getLon())
                .dt(x.getDt())
                .mainFeelsLike(x.getMain().getFeels_like())
                .mainHumidity(x.getMain().getHumidity())
                .mainPressure(x.getMain().getPressure())
                .mainTemp(x.getMain().getTemp())
                .mainTemp_max(x.getMain().getTemp_max())
                .mainTempMin(x.getMain().getTemp_min())
                .name(x.getName())
                .sysCountry(x.getSys().getCountry())
                .sysSunrise(x.getSys().getSunrise())
                .sysSunset(x.getSys().getSunset())
                .sysType(x.getSys().getType())
                .timezone(x.getTimezone())
                .visibility(x.getVisibility())
                .windDeg(x.getWind().getDeg())
                .windSpeed(x.getWind().getSpeed())
                .weather(new ArrayList<>())
                .build();

        List<OWMDetailWeather> lst = x.getWeather().stream().map(WeatherMapper::toOWMDetailWeather)
                .map(weatherDetail -> {
                    owmDetail.addToWeatherDetail(weatherDetail);
                    return weatherDetail;
                })
                .collect(Collectors.toList());
        owmDetail.setWeather(lst);
        return owmDetail;
    }

}

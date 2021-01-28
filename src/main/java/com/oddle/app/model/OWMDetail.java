package com.oddle.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "owm_detail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OWMDetail {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "base")
    private String base;

    @Column(name = "visibility")
    private Long visibility;

    @Column(name = "dt")
    private Long dt;

    @Column(name = "timezone")
    private Integer timezone;

    @Column(name = "name")
    private String name;

    @Column(name = "cod")
    private Integer cod;

    @Column(name = "coord_lon")
    private Long coordLon;

    @Column(name = "coord_lat")
    private Long coordLat;

    @OneToMany(mappedBy="owmDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OWMDetailWeather> weather = new ArrayList<>();

    @Column(name = "main_temp")
    private Double mainTemp;

    @Column(name = "main_feels_like")
    private Double mainFeelsLike;

    @Column(name = "main_temp_min")
    private Double mainTempMin;

    @Column(name = "main_temp_max")
    private Double mainTemp_max;

    @Column(name = "main_pressure")
    private Double mainPressure;

    @Column(name = "main_humidity")
    private Double mainHumidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_deg")
    private Double windDeg;

    @Column(name = "clouds_all")
    private Integer cloudsAll;

    @Column(name = "sys_type")
    private Integer sysType;

    @Column(name = "sys_country")
    private String sysCountry;

    @Column(name = "sys_sunrise")
    private Long sysSunrise;

    @Column(name = "sys_sunset")
    private Long sysSunset;

    public void addToWeatherDetail(OWMDetailWeather child) {
        child.setOwmDetail(this);
        this.weather.add(child);
    }

}

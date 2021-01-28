package com.oddle.app.model;

import com.oddle.app.common.WeatherProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "weather_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherLog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "weather_provider")
    @Enumerated(EnumType.STRING)
    private WeatherProvider weatherProvider;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owm_detail_id", referencedColumnName = "id")
    private OWMDetail OWMDetail;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public com.oddle.app.model.OWMDetail getOWMDetail() {
        return OWMDetail;
    }

    public void setOWMDetail(com.oddle.app.model.OWMDetail OWMDetail) {
        this.OWMDetail = OWMDetail;
    }
}

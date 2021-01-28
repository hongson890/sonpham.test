package com.oddle.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "owm_detail_weather")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OWMDetailWeather {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name="owm_detail_id", nullable=false)
    @JsonBackReference
    private OWMDetail owmDetail;
}

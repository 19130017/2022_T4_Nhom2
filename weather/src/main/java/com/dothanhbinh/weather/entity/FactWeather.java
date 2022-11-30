package com.dothanhbinh.weather.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity
@Getter
@Setter
@Table(name = "fact_weather")
public class FactWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "natural_key")
    private String natural_key;


    @ManyToOne()
    @JoinColumn(name = "forecast_date_id", referencedColumnName = "date_id")
    DateDim forecast_date;

    @ManyToOne()
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    ProvinceDim province;
//    @Column(name = "forecast_date_id")
//    private Integer forecast_date_id;
//
//    @Column(name = "province_id")
//    private Integer province_id;

    @Column(name = "description")
    private String description;

    @Column(name = "min_temperature")
    private Integer min_temperature;

    @Column(name = "max_temperature")
    private Integer max_temperature;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "wind_speed")
    private Double wind_speed;

    @Column(name = "sunrise")
    private String sunrise;

    @Column(name = "sundown")
    private String sundown;

    @Column(name = "is_delete")
    private Boolean is_delete;

    @Column(name = "expired_date")
    private Integer expired_date;

    @Column(name = "expired_time")
    private Integer expired_time;

}

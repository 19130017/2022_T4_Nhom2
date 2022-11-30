package com.dothanhbinh.weather.dto;

import com.dothanhbinh.weather.entity.FactWeather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FactWeatherDto {
    public Long id;
    public String natural_key;
    public DateDimDto forecast_date;
    public ProvinceDimDto province;
    public String description;
    public Integer min_temp;
    public Integer max_temp;
    public Integer humidity;
    public Double wind_speed;
    public String sunrise;
    public String sundown;
    public Boolean is_delete;
    private Integer expired_date;
    private Integer expired_time;
}

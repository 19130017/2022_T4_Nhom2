package com.dothanhbinh.weather.mapper;

import com.dothanhbinh.weather.dto.FactWeatherDto;
import com.dothanhbinh.weather.entity.FactWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("weatherMapper")
public class WeatherMapper implements IMapper<FactWeather, FactWeatherDto> {

    private final DateDimMapper dateDimMapper;
    private final ProvinceDimMapper provinceDimMapper;

    @Autowired
    public WeatherMapper(DateDimMapper dateDimMapper, ProvinceDimMapper provinceDimMapper) {
        this.dateDimMapper = dateDimMapper;
        this.provinceDimMapper = provinceDimMapper;
    }

    @Override
    public FactWeather toEntity(FactWeatherDto dto) {
        if (dto == null) return null;
        return FactWeather.builder()
                .id(dto.getId())
                .natural_key(dto.getNatural_key())
                .forecast_date(dateDimMapper.toEntity(dto.getForecast_date()))
                .province(provinceDimMapper.toEntity(dto.getProvince()))
                .description(dto.getDescription())
                .min_temperature(dto.getMin_temp())
                .max_temperature(dto.getMax_temp())
                .humidity(dto.getHumidity())
                .wind_speed(dto.getWind_speed())
                .sunrise(dto.getSunrise())
                .sundown(dto.getSundown())
                .is_delete(dto.getIs_delete())
                .expired_date(dto.getExpired_date())
                .expired_time(dto.getExpired_time())
                .build();
    }

    @Override
    public FactWeatherDto toDto(FactWeather entity) {
        if (entity == null) return null;
        return FactWeatherDto.builder()
                .id(entity.getId())
                .natural_key(entity.getNatural_key())
                .forecast_date(dateDimMapper.toDto(entity.getForecast_date()))
                .province(provinceDimMapper.toDto(entity.getProvince()))
                .description(entity.getDescription())
                .min_temp(entity.getMin_temperature())
                .max_temp(entity.getMax_temperature())
                .humidity(entity.getHumidity())
                .wind_speed(entity.getWind_speed())
                .sunrise(entity.getSunrise())
                .sundown(entity.getSundown())
                .is_delete(entity.getIs_delete())
                .expired_date(entity.getExpired_date())
                .expired_time(entity.getExpired_time())
                .build();
    }

    @Override
    public List<FactWeather> toEntities(List<FactWeatherDto> factWeatherDtos) {
        List<FactWeather> weathers = new ArrayList<>();
        for (FactWeatherDto factWeatherDto : factWeatherDtos) {
            weathers.add(toEntity(factWeatherDto));
        }
        return weathers;
    }

    @Override
    public List<FactWeatherDto> toDtos(List<FactWeather> entities) {
        List<FactWeatherDto> weatherDtos = new ArrayList<>();
        for (FactWeather entity : entities) {
            weatherDtos.add(toDto(entity));
        }
        return weatherDtos;
    }
}

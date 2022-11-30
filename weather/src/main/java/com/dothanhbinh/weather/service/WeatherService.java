package com.dothanhbinh.weather.service;

import com.dothanhbinh.weather.dto.FactWeatherDto;
import com.dothanhbinh.weather.entity.FactWeather;
import com.dothanhbinh.weather.mapper.WeatherMapper;
import com.dothanhbinh.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    WeatherRepository weatherRepository;
    WeatherMapper weatherMapper;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    public List<FactWeatherDto> findAll() {
        List<FactWeather> listEntity = weatherRepository.findAll();
        return weatherMapper.toDtos(listEntity);
    }

    public FactWeatherDto findById(Long id) {
        Optional<FactWeather> factWeather = weatherRepository.getWeather(id);
        return weatherMapper.toDto(factWeather.get());
    }

    public List<FactWeatherDto> findByExpiredDate() {
        return weatherMapper.toDtos(weatherRepository.findByExpiredDate());
    }

    public FactWeatherDto getWeatherCapital() {
        Optional<FactWeather> factWeather = weatherRepository.findWeatherCapital();
        return weatherMapper.toDto(factWeather.get());
    }
}

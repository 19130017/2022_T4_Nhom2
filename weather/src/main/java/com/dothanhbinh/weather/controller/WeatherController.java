package com.dothanhbinh.weather.controller;

import com.dothanhbinh.weather.dto.FactWeatherDto;
import com.dothanhbinh.weather.entity.FactWeather;
import com.dothanhbinh.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin()
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/getAll")
    public List<FactWeatherDto> findAll() {
        List<FactWeatherDto> listWeather = weatherService.findAll();
        if (listWeather.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return listWeather;
    }

    @GetMapping(value = "/{id}/info")
    public FactWeatherDto findById(@PathVariable(value = "id") Long id) {
        FactWeatherDto factWeather = weatherService.findById(id);
        return factWeather;
    }

    @GetMapping(value = "/getCurrentNoExpired")
    public List<FactWeatherDto> getListNoExpired() {
        return weatherService.findByExpiredDate();
    }


    @GetMapping(value="/getWeatherCapital")
    public FactWeatherDto getWeatherCapital() {
        return weatherService.getWeatherCapital();
    }
}

package vn.weather.service;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.weather.model.FactWeather;

public interface WeatherService extends JpaRepository<FactWeather, Long> {
}

package com.dothanhbinh.weather.repository;

import com.dothanhbinh.weather.entity.FactWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<FactWeather, Long> {
    @Query(value = "SELECT * FROM fact_weather f \n" +
            "join date_dim d on f.forecast_date_id = d.date_id\n" +
            "where d.date= date(now()) and f.expired_date=1462", nativeQuery = true)
    List<FactWeather> findByExpiredDate();

    @Query(value = "SELECT * FROM fact_weather where id =?1", nativeQuery = true)
    Optional<FactWeather> getWeather(Long id);

    @Query(value = "SELECT * FROM fact_weather f \n" +
            "join province_dim p on p.id =f.province_id \n" +
            "join date_dim d on f.forecast_date_id = d.date_id\n" +
            "where p.name = 'Hà Nội' and d.date= date(now()) and f.expired_date=1462", nativeQuery = true)
    Optional<FactWeather> findWeatherCapital();
}

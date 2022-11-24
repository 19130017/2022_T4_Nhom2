package vn.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FactWeather implements Serializable {
    private static final long serialVersionUID = 1L;
    private String natural_key;
    private int forecast_date_id;
    private int province_id;
    private String description;
    private int min_temperature;
    private int max_temperature;
    private int humidity;
    private double wind_speed;
    private String sunrise;
    private String sundown;
    private boolean is_delete;
    private int expired_date;
    private int expired_time;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public FactWeather() {
        id = 0;
    }

    public FactWeather(String natural_key, int forecast_date_id, int province_id, String description, int min_temperature, int max_temperature, int humidity, double wind_speed, String sunrise, String sundown, boolean is_delete, int expired_date, int expired_time, long id) {
        this.natural_key = natural_key;
        this.forecast_date_id = forecast_date_id;
        this.province_id = province_id;
        this.description = description;
        this.min_temperature = min_temperature;
        this.max_temperature = max_temperature;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.sunrise = sunrise;
        this.sundown = sundown;
        this.is_delete = is_delete;
        this.expired_date = expired_date;
        this.expired_time = expired_time;
        this.id = id;
    }

    public String getNatural_key() {
        return natural_key;
    }

    public void setNatural_key(String natural_key) {
        this.natural_key = natural_key;
    }

    public int getForecast_date_id() {
        return forecast_date_id;
    }

    public void setForecast_date_id(int forecast_date_id) {
        this.forecast_date_id = forecast_date_id;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMin_temperature() {
        return min_temperature;
    }

    public void setMin_temperature(int min_temperature) {
        this.min_temperature = min_temperature;
    }

    public int getMax_temperature() {
        return max_temperature;
    }

    public void setMax_temperature(int max_temperature) {
        this.max_temperature = max_temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSundown() {
        return sundown;
    }

    public void setSundown(String sundown) {
        this.sundown = sundown;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(int expired_date) {
        this.expired_date = expired_date;
    }

    public int getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(int expired_time) {
        this.expired_time = expired_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

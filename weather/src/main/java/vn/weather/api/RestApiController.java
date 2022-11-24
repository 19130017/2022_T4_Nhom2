package vn.weather.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.weather.model.FactWeather;
import vn.weather.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    WeatherService weatherService;

    @GetMapping(value = "/weather/")

    public ResponseEntity<List<FactWeather>> listAllWeather() {
        List<FactWeather> listWeather = weatherService.findAll();
        if (listWeather.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<FactWeather>>(listWeather, HttpStatus.OK);
    }

    @GetMapping(value = "/weather/{id}")
    public FactWeather findContact(@PathVariable("id") long id) {
        FactWeather weather = weatherService.getOne(id);
        if (weather == null) {
            ResponseEntity.notFound().build();
        }
        return weather;
    }

}

package com.fiap.enterprise.controller;

import com.fiap.enterprise.integration.stormglass.tide.TideApiResponse;
import com.fiap.enterprise.integration.weatherapi.weather.WeatherApiResponse;
import com.fiap.enterprise.model.TemperatureData;
import com.fiap.enterprise.response.WeatherForecastResponse;
import com.fiap.enterprise.service.WeatherServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

    @Autowired
    WeatherServiceImpl weatherServiceImpl;

    @GetMapping("/thunderstorm")
    public ResponseEntity<WeatherForecastResponse> hasStormForecast(@RequestParam String city) {
        var response = weatherServiceImpl.getStormInfo(city);
       return response != null ? ResponseEntity.ok(response) : new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cityTemperature")
    public ResponseEntity<TemperatureData> getCityTemperatureData(@RequestParam String city) {
        TemperatureData temperatureData = weatherServiceImpl.getCityTemperatureData(city);

        return temperatureData != null ? ResponseEntity.ok(temperatureData) : new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherApiResponse> getWeatherData(@RequestParam String city) {
        WeatherApiResponse weatherData = weatherServiceImpl.getWeatherData(city);

        return weatherData != null ? ResponseEntity.ok(weatherData) : new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tide-extremes")
    public ResponseEntity<TideApiResponse> getTideExtremes(
            @RequestParam double lat,
            @RequestParam double lng
    ) {
        TideApiResponse response = new TideApiResponse();
        try {
            response = weatherServiceImpl.getTideData(lat, lng);
        }catch (IllegalArgumentException e){
            return new ResponseEntity("Argumento invalido: " + e, HttpStatus.BAD_REQUEST);
        }
        if(response != null) return ResponseEntity.ok(response);

        return null;
    }

}

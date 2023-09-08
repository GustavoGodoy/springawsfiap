package com.fiap.enterprise.service;

import com.fiap.enterprise.integration.stormglass.tide.TideApiResponse;
import com.fiap.enterprise.integration.weatherapi.weather.WeatherApiResponse;
import com.fiap.enterprise.model.TemperatureData;
import com.fiap.enterprise.response.WeatherForecastResponse;

import java.util.List;

public interface WeatherService {
    WeatherForecastResponse getStormInfo(String city);
    TemperatureData getCityTemperatureData(String city);
    WeatherApiResponse getWeatherData(String city);
    TideApiResponse getTideData(double lat, double lng);
}

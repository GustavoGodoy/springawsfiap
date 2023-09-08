package com.fiap.enterprise.service;

import com.fiap.enterprise.integration.stormglass.tide.TideApiResponse;
import com.fiap.enterprise.integration.weatherapi.weather.WeatherApiResponse;
import com.fiap.enterprise.model.TemperatureData;
import com.fiap.enterprise.integration.weatherapi.forecast.WeatherForecastApiResponse;
import com.fiap.enterprise.response.WeatherForecastResponse;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${openweathermap.apikey}") // Store API key in application.properties or application.yml
    private String apiKey;

    @Value("${stormglass.authorization}")
    private String authToken;

    private static final Pattern LATITUDE_PATTERN = Pattern.compile("^-?([1-8]?\\d(?:\\.\\d{1,18})?|90(?:\\.0{1,18})?)$");

    private static final Pattern LONGITUDE_PATTERN = Pattern.compile("^-?((?:1[0-7]|[1-9])?\\d(?:\\.\\d{1,18})?|180(?:\\.0{1,18})?)$");

    private String forecastUrl = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&lang=pt_br&units=metric";

    private String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&lang=pt_br&units=metric";

    private String tideUrl = "https://api.stormglass.io/v2/tide/extremes/point?lat=%f&lng=%f&params=tide";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherForecastResponse getStormInfo(String city) {
        WeatherForecastApiResponse response = new WeatherForecastApiResponse();
        try {
            response = restTemplate.getForObject(String.format(forecastUrl, city, apiKey), WeatherForecastApiResponse.class);
        }catch (HttpClientErrorException exception){
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) return null;
        }
        WeatherForecastResponse weatherResponse = new WeatherForecastResponse();

        if (response != null) {
            weatherResponse.setLatitude(response.getCity().getCoord().getLat());
            weatherResponse.setLongitude(response.getCity().getCoord().getLon());

            boolean hasStorm = response.getList().stream()
                    .anyMatch(forecast -> "Thunderstorm".equalsIgnoreCase(forecast.getWeather().get(0).getMain()));

            weatherResponse.setHasStorm(hasStorm);
        }

        return weatherResponse;
    }

    @Override
    public TemperatureData getCityTemperatureData(String city) {
        WeatherForecastApiResponse response = new WeatherForecastApiResponse();
        try {
            response = restTemplate.getForObject(String.format(forecastUrl, city, apiKey), WeatherForecastApiResponse.class);
        }catch (HttpClientErrorException exception){
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) return null;
        }
        TemperatureData temperatureData = new TemperatureData();

        if (response != null) {
            List<TemperatureData.HourlyTemperature> hourlyTemps = response.getList().stream()
                    .map(item -> new TemperatureData.HourlyTemperature(item.getDt() * 1000L, item.getMain().getTemp()))
                    .collect(Collectors.toList());

            temperatureData.setTemperatures(hourlyTemps);
            temperatureData.setLastUpdate(new Date());
        }

        return temperatureData;
    }

    @Override
    public WeatherApiResponse getWeatherData(String city) {
        String apiUrl = String.format(weatherUrl, city, apiKey);
        try {
            return restTemplate.getForObject(apiUrl, WeatherApiResponse.class);
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) return null;
        }
        return null;
    }

    public TideApiResponse getTideData(double latitude, double longitude) {

        String url = String.format(tideUrl,
                latitude, longitude);
        url = url.replace(",", ".");

        if (!LATITUDE_PATTERN.matcher(String.valueOf(latitude)).matches()) {
            throw new IllegalArgumentException("Invalid latitude value.");
        }

        if (!LONGITUDE_PATTERN.matcher(String.valueOf(longitude)).matches()) {
            throw new IllegalArgumentException("Invalid longitude value.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<TideApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TideApiResponse.class);

        return response.getBody();
    }


}

package com.fiap.enterprise.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastResponse {

    private boolean hasStorm;
    private double latitude;
    private double longitude;

}

package com.fiap.enterprise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TemperatureData {
    private List<HourlyTemperature> temperatures;
    private Date lastUpdate;


    @Getter
    @Setter
    @AllArgsConstructor
    public static class HourlyTemperature {
        private long time;
        private double temperature;

    }
}




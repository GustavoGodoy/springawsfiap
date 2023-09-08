package com.fiap.enterprise.integration.stormglass.tide;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cost",
        "dailyQuota",
        "datum",
        "end",
        "lat",
        "lng",
        "requestCount",
        "start",
        "station"
})
public class Meta {

    @JsonProperty("cost")
    private Integer cost;
    @JsonProperty("dailyQuota")
    private Integer dailyQuota;
    @JsonProperty("datum")
    private String datum;
    @JsonProperty("end")
    private String end;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
    @JsonProperty("requestCount")
    private Integer requestCount;
    @JsonProperty("start")
    private String start;
    @JsonProperty("station")
    private Station station;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cost")
    public Integer getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @JsonProperty("dailyQuota")
    public Integer getDailyQuota() {
        return dailyQuota;
    }

    @JsonProperty("dailyQuota")
    public void setDailyQuota(Integer dailyQuota) {
        this.dailyQuota = dailyQuota;
    }

    @JsonProperty("datum")
    public String getDatum() {
        return datum;
    }

    @JsonProperty("datum")
    public void setDatum(String datum) {
        this.datum = datum;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public Double getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(Double lng) {
        this.lng = lng;
    }

    @JsonProperty("requestCount")
    public Integer getRequestCount() {
        return requestCount;
    }

    @JsonProperty("requestCount")
    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("station")
    public Station getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(Station station) {
        this.station = station;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

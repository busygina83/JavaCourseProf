package jdev.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

//import java.io.IOException;

/**
 * Created by jdev on 06.03.2017.
 */
public class Point {

    private String getDateFormat(long TimeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
        Date resultDate = new Date(TimeMillis);
        return sdf.format(resultDate);
    }

    private String autoId; //ID грузовика
    private long time; //текущее время
    private double lat; //широта
    private double lon; //долгота
    private double azimuth; //угол уклона направления грузовика от северного вектора
    private double azimuthDelta; //разница угла уклона в сек на поворотах
    private double duration; //расстояние за сек на прямых
    private double speed; //текущая скорость грузовика
    private double speedUp; //ускорение грузовика

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) { this.lat = lat; }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public double getSpeedUp() { return speedUp; }

    public void setSpeedUp(double speedUp) { this.speedUp = speedUp; }

    public double getAzimuth() { return azimuth; }

    public void setAzimuth(double azimuth) { this.azimuth = azimuth; }

    public double getAzimuthDelta() { return azimuthDelta; }

    public void setAzimuthDelta(double azimuthDelta) { this.azimuthDelta = azimuthDelta; }

    public double getDuration() { return duration; }

    public void setDuration(double duration) { this.duration = duration; }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public Object toObject() {
        return this;
    }

    @Override
    public String toString() {
        return "{ " +
                "autoId='" + autoId + '\'' +
                ", time='" + getDateFormat(time) + '\'' +
                ", lat=" + String.format("%.5f",lat) +
                ", lon=" + String.format("%.5f",lon) +
                ", azimuth=" + String.format("%.5f",azimuth) +
                ", azimuth=" + String.format("%.5f",azimuthDelta) +
                ", azimuth=" + String.format("%.5f",duration) +
                ", speed=" + String.format("%.5f",speed) +
                ", speedUp=" + String.format("%.5f",speedUp) +
                " }";
    }

}

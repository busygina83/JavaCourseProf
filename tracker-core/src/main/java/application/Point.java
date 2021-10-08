package application;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Document(collection="GpsEmulator")
public class Point {

    public String getDateFormat(long TimeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
        Date resultDate = new Date(TimeMillis);
        return sdf.format(resultDate);
    }

    @Id
    @GeneratedValue(strategy = AUTO)
    @Field(value = "id")
    double id;
    @Field(value = "autoid")
    String autoId; //ID грузовика
    @Field(value = "time")
    long time; //текущее время
    @Field(value = "lat")
    double lat; //широта
    @Field(value = "lon")
    double lon; //долгота
    @Field(value = "azimuth")
    double azimuth; //угол уклона направления грузовика от северного вектора
    @Field(value = "azimuthdelta")
    double azimuthDelta; //разница угла уклона в сек на поворотах
    @Field(value = "duration")
    double duration; //расстояние за сек на прямых
    @Field(value = "speed")
    double speed; //текущая скорость грузовика
    @Field(value = "speedup")
    double speedUp; //ускорение грузовика

    public double getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getAutoId() {return autoId;}
    public void setAutoId(String autoId) {this.autoId = autoId;}

    public double getTime() {return time;}
    public void setTime(long time) {this.time = time;}

    public double getLat() {return lat;}
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() {return lon;}
    public void setLon(double lon) {this.lon = lon;}

    public double getSpeed() {return speed;}
    public void setSpeed(double speed) {this.speed = speed;}

    public double getSpeedUp() {return speedUp;}
    public void setSpeedUp(double speedUp) {this.speedUp = speedUp;}

    public double getAzimuth() {return azimuth;}
    public void setAzimuth(double azimuth) {this.azimuth = azimuth;}

    public double getAzimuthDelta() {return azimuthDelta;}
    public void setAzimuthDelta(double azimuthDelta) {this.azimuthDelta = azimuthDelta;}

    public double getDuration() {return duration;}
    public void setDuration(double duration) {this.duration = duration;}

    @Override
    public String toString() {
        return "{" +
                "\n\"id\": " + String.format("%.5f",id) +
                "\n\"autoId\": '" + "\"" + autoId + "\"" +
                "\n\"time\": '" + "\"" + getDateFormat(time) + "\"" +
                "\n\"lat\": " + "\"" + String.format("%.5f",lat) + "\"" +
                "\n\"lon\": " + "\"" + String.format("%.5f",lon) + "\"" +
                "\n\"azimuth\": " + "\"" + String.format("%.5f",azimuth) + "\"" +
                "\n\"azimuthDelta\": " + "\"" + String.format("%.5f",azimuthDelta) + "\"" +
                "\n\"duration\": " + "\"" + String.format("%.5f",duration) + "\"" +
                "\n\"speed\": " + "\"" + String.format("%.5f",speed) + "\"" +
                "\n\"speedUp\": " + "\"" + String.format("%.5f",speedUp) + "\"" +
                "\n}";
    }

}

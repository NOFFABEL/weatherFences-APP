package com.example.entity;

import org.jetbrains.annotations.NotNull;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Weather {

    private String city, country;
    private LocalDateTime date_in;
    private Temperature temperature;
    private Wind wind;
    private String desc, icon;

    private Bitmap iconData=null;

    @TargetApi(Build.VERSION_CODES.O)
    public Weather() {
        this.date_in = LocalDateTime.now(ZoneId.systemDefault());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getDate_in() {
        return date_in;
    }

    public void setDate_in(LocalDateTime date_in) {
        this.date_in = date_in;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Bitmap getIconData() {
        return iconData;
    }

    public void setIconData(Bitmap iconData) {
        this.iconData = iconData;
    }

    @NotNull
    @Override
    public String toString() {
        String val = iconData != null ? "not null" : "null";
        return "Weather{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", date_in=" + date_in +
                ", " + temperature.toString() +
                ", " + wind.toString() +
                ", desc='" + desc + '\'' +
                ", icon='" + icon + '\'' +
                ", iconData=" + val +
                '}';
    }
}

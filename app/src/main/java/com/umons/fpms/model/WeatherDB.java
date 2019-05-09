package com.umons.fpms.model;

import com.example.entity.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class WeatherDB {
    private int usr_id;
    private float temp, temp_min, temp_max;
    private String city, country, date_in, icon;


    public WeatherDB(int usr_id, float temp, float temp_min, float temp_max, String city, String country, String date_in, String icon) {

        this.usr_id = usr_id;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.city = city;
        this.country = country;
        this.date_in = date_in;
        this.icon = icon;
    }



    @Override
    public String toString() {
        JSONObject weather = new JSONObject();
        try {
            weather.putOpt("temp", temp)
                    .putOpt("temp_min", temp_min)
                    .putOpt("temp_max", temp_max)
                    .putOpt("city", city)
                    .putOpt("country", country)
                    .putOpt("usr_id", usr_id)
                    .putOpt("date_in", date_in)
                    .putOpt("icon", icon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather.toString();
    }

    public int getUsr_id() {
        return usr_id;
    }

    public WeatherDB setUsr_id(int usr_id) {
        this.usr_id = usr_id;
        return this;
    }

    public float getTemp() {
        return temp;
    }

    public WeatherDB setTemp(float temp) {
        this.temp = temp;
        return this;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public WeatherDB setTemp_min(float temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public WeatherDB setTemp_max(float temp_max) {
        this.temp_max = temp_max;
        return this;
    }

    public String getCity() {
        return city;
    }

    public WeatherDB setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public WeatherDB setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getDate_in() {
        return date_in;
    }

    public WeatherDB setDate_in(String date_in) {
        this.date_in = date_in;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public WeatherDB setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}

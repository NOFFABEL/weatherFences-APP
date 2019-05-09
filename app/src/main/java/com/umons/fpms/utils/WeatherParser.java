package com.umons.fpms.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.entity.Temperature;
import com.example.entity.Weather;
import com.example.entity.Wind;
import static com.umons.fpms.utils.Constants.FAHRENHEIT_TO_CELCIUS;

public class WeatherParser {

    public static Weather getWeather(String data) throws JSONException {
        Log.i("String Data", data);
        // We take our Weather as a JSONObject.
        JSONObject jObj = new JSONObject(data);
        Log.i("JSON Data", jObj.toString());
        // Create a Weather
        Temperature temp = new Temperature();
        Wind wind = new Wind();
        Weather weather = new Weather();

        // general
        JSONArray jObj_a = jObj.optJSONArray("weather");
        JSONObject jsonWeather = null;
        if(jObj_a != null && jObj_a.length() > 0 ) {
            jsonWeather = jObj_a.optJSONObject(0);
        }
        if (jsonWeather != null){
            weather.setIcon(jsonWeather.optString("icon"));
            weather.setDesc(jsonWeather.optString("description"));
            weather.setCity(jObj.optString("name"));
            weather.setCountry(jObj.optJSONObject("sys").optString("country"));

            // temperature
            JSONObject mainObj = jObj.optJSONObject("main");
            if( mainObj != JSONObject.NULL) {
                temp.setHumidity((float) Math.round(mainObj.optDouble("humidity")));
                temp.setPressure((float) Math.round(mainObj.optDouble("pressure")));
                temp.setTemp(convertTemp((float) mainObj.optDouble("temp")));
                temp.setTemp_max(convertTemp((float) mainObj.optDouble("temp_max")));
                temp.setTemp_min(convertTemp((float) mainObj.optDouble("temp_min")));

                weather.setTemperature(temp);
            }

            Log.i("Temperature", temp.toString());

            // wind
            JSONObject windObj = jObj.optJSONObject("wind");
            if(windObj != null) {
                wind.setDeg((float) windObj.optDouble("deg"));
                wind.setSpeed((float) windObj.optDouble("speed"));

                weather.setWind(wind);
            }
        }
        Log.i("WeatherParser", weather.toString());
        return weather;
    }

    private static float convertTemp(final float temp){
        return (float) Math.round(temp + FAHRENHEIT_TO_CELCIUS);
    }
}

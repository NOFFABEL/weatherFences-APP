package com.umons.fpms.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.entity.Weather;
import com.umons.fpms.model.WeatherHttpClient;
import com.umons.fpms.utils.WeatherParser;

import org.json.JSONException;

public class WeatherController extends AsyncTask<String, Void, Weather> {


    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param args The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Weather doInBackground(String... args) {
        Weather weather = new Weather();
        WeatherHttpClient http = new WeatherHttpClient();
        String data = http.getWeatherData(args[0]);

        try {
            weather = WeatherParser.getWeather(data);

            // Now let's take the iconData corresponding to the code in weather.getIcon()
            weather.setIconData(http.getImageFromCode(weather.getIcon()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weather;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param weather The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);

        if(weather.getIconData() != null) {
            ;
        }
    }
}

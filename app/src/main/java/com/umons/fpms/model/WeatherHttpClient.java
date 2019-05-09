package com.umons.fpms.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.umons.fpms.utils.Constants.CONNECT_TIMEOUT;
import static com.umons.fpms.utils.Constants.READ_TIMEOUT;
import static com.umons.fpms.utils.Constants.WEATHER_BASE_URL;
import static com.umons.fpms.utils.Constants.WEATHER_IMG_URL;

public class WeatherHttpClient {


    public WeatherHttpClient() {
        super();
    }

    public String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream iStream = null;
        String result = null;

        StringBuilder strBuilder;
        try {
            // We can establish connection to the API server
            con = getRequest(new URL(WEATHER_BASE_URL+location));
            int httpCode = con.getResponseCode();
            Log.i("HTTP Code", ""+ httpCode);
            // We can read the response
            strBuilder = new StringBuilder();

            if(httpCode == HttpURLConnection.HTTP_OK) {
                iStream = con.getInputStream();
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(iStream));
                String line;
                while ((line = bfReader.readLine()) != null)
                    strBuilder.append(line).append("\r\n");
            } else {
                String msg = con.getResponseMessage();
                Log.i("HTTP Message", ""+ msg);
                strBuilder.append(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            strBuilder = null;
        } catch (IOException e) {
            e.printStackTrace();
            strBuilder = null;
        } finally {
            try {
                if(iStream != null) iStream.close();
                if(con!=null) con.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (strBuilder != null) return strBuilder.toString();
        else return null;
    }

    public Bitmap getImageFromCode(String code){
        code += ".png";
        HttpURLConnection con = null;
        InputStream iStream = null;
        /*byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();*/
        Bitmap bitmap = null;

        try {
            con = getRequest(new URL(WEATHER_IMG_URL+code));
            int httpCode = con.getResponseCode();
            Log.i("HTTP Code", ""+ httpCode);

            if(httpCode == HttpURLConnection.HTTP_OK) {
                // We can read the response
                iStream = con.getInputStream();
                bitmap = BitmapFactory.decodeStream(iStream);
                //bitmap.reconfigure(120, 120, null);
                // bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
            } else {
                String msg = con.getResponseMessage();
                Log.i("HTTP Message", ""+ msg);
                return null;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (iStream != null) iStream.close();
                if (con != null) con.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i("IconData", bitmap != null ? "bitmap not null" : "bitmap null");
        return bitmap;
    }

    public HttpURLConnection getRequest(URL url) throws IOException {
        HttpURLConnection con = null;
        url = new URL(url.toString());
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoInput(true);
        //con.setDoOutput(true);
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
        con.connect();

        return con;
    }

    public HttpURLConnection postRequest(URL url) throws IOException {
        HttpURLConnection con = null;
        url = new URL(url.toString());
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
        con.connect();

        return con;
    }

}

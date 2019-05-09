package com.umons.fpms.utils;

import com.umons.fpms.model.UserDB;

public final class Constants {
    public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?" +
            "apikey=80bbb7a608e58dad6558b3a82fa6b3ef&" +
            "q=";
    public static final String WEATHER_IMG_URL = "http://api.openweathermap.org/img/w/";

    private static final int HOST_API_PORT_NUM = 8088;
    //private static final String HOST_IP = "192.168.0.15";
    private static final String HOST_IP = "192.168.43.81";

    public static final int CONNECT_TIMEOUT = 15000;
    public static final int READ_TIMEOUT = 25000;

    static final float FAHRENHEIT_TO_CELCIUS = (float) -273.15;

    /**
     * Return full url to connect to the host.
     * Take care of the HOST_IP.
     * @return
     */
    public static String getHostUrl() {
        return "http://" + HOST_IP + ":" + HOST_API_PORT_NUM + "/";
    }
}

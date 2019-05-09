package com.umons.fpms.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Transformer {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateFromString(String date_in) {
        LocalDateTime date = LocalDateTime.parse(date_in);
        return date.toString();
    }
}

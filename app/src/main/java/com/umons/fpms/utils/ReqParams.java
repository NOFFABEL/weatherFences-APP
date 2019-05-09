package com.umons.fpms.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 *
 */
public class ReqParams {
    private HashMap<String, String> params;

    public ReqParams() {
        params = new HashMap<String, String>();
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public ReqParams addParams(String key, String value) {
        this.params.put(key, value);

        return this;
    }

    @Override
    public String toString() {
        StringBuilder p = new StringBuilder();
        int i=0;
        for(String key: params.keySet()) {
            if(i != 0) p.append("&");
            //p.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
            p.append(key).append("=").append(params.get(key));
        ++i;
        }

        return p.toString();
    }
}

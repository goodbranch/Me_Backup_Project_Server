package com.me.backup.util;

import com.google.gson.Gson;

public class JsonUtil {

    private static Gson gson = new Gson();


    public static String toJson(Object object) {

        return gson.toJson(object);

    }

}

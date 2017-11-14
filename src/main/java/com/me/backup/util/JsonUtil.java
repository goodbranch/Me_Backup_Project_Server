package com.me.backup.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {

    private static Gson gson = new Gson();


    public static String toJson(Object object) {

        return gson.toJson(object);

    }


    public static <T> T fromJson(String jsonStr, Type type) {

        if (StringUtil.isEmpty(jsonStr)) {
            return null;
        }

        try {
            return gson.fromJson(jsonStr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}

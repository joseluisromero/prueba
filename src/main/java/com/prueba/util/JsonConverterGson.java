package com.prueba.util;

import com.google.gson.Gson;

public class JsonConverterGson {
    private static final Gson gson = new Gson();

    public static Object jsonToObject(String json) {
        return new Object();
    }

    public static String objectToJson(Object object) {
        String json = gson.toJson(object);
        return json;
    }
}

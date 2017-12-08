package com.me.backup.util;

import org.apache.commons.lang3.text.StrBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WebServletUtil {


    public static Map<String, String> getRequestMap(HttpServletRequest request) {

        final String requestBody = getRequestPostStr(request);

        return toMap(requestBody);
    }


    public static Map<String, String> toMap(String str) {

        if (StringUtil.isEmpty(str)) {
            return null;
        }

        String[] split_params = str.split("&");
        HashMap<String, String> params = new HashMap<String, String>();
        for (String paramStr : split_params) {
            String[] key_value = paramStr.split("=");
            if (key_value.length == 2) {
                params.put(key_value[0], key_value[1]);
            }
        }

        return params;
    }


    public static String getRequestPostStr(HttpServletRequest request) {

        try {
            InputStream stream = request.getInputStream();
            final String charsetName = request.getCharacterEncoding() == null ? "utf-8" : request.getCharacterEncoding();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, charsetName));
            StringBuilder strBuilder = new StringBuilder();
            String read;

            while ((read = bufferedReader.readLine()) != null) {
                strBuilder.append(read);
            }

            return strBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

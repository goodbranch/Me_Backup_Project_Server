package com.me.backup.util;

public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str == null) {
            return true;
        }
        return str.length() == 0;
    }

}

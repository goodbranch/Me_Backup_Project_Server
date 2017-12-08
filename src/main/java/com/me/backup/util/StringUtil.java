package com.me.backup.util;

public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str == null) {
            return true;
        }
        return str.length() == 0;
    }


    public static boolean equals(String str, String compareStr) {

        if (str == null || compareStr == null) {
            return false;
        }

        return str.equals(compareStr);

    }


}

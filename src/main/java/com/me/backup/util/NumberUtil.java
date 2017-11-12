package com.me.backup.util;

public class NumberUtil {


    public static int String2Int(String intStr) {

        try {
            return Integer.valueOf(intStr);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return -1;
    }


    public static long String2Long(String longStr) {

        try {
            return Long.valueOf(longStr);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return -1;
    }

}

package com.me.backup.util;

import java.util.regex.Pattern;

public class ProjectUtil {


    /**
     * 判断是不是手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isMobilePhoneNum(String phoneNum) {
        if (StringUtil.isEmpty(phoneNum)) {
            return false;
        }
        if (Pattern.matches("^1[34578]\\d{9}$", phoneNum)) {  //手机号码
            return true;
        }
        return false;
    }

}

package com.me.backup.controller;

import com.me.backup.util.JsonUtil;

public class BaseController {


    protected Object toResponse(int code, String msg, Object data) {

        ApiResponseResult apiResponseResult = new ApiResponseResult();
        apiResponseResult.code = code;
        apiResponseResult.msg = msg;
        apiResponseResult.data = data;

        return apiResponseResult;
    }
}

package com.me.backup.controller;

public class BaseController {


    protected Object toResponse(int code, String msg, Object data) {

        ApiResponseResult apiResponseResult = new ApiResponseResult();
        apiResponseResult.code = code;
        apiResponseResult.msg = msg;
        apiResponseResult.data = data;

        return apiResponseResult;
    }

    protected Object toResponse(int code, String msg) {

        return toResponse(code, msg, null);
    }

    protected Object toResponse(int code) {

        return toResponse(code, null, null);
    }
}

package com.svalero.supermercadoAPI.domain;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse(int errorCode, String errorMessage){
        code = errorCode;
        message = errorMessage;
    }

    private ErrorResponse(int code, String errorMessage, Map<String, String> errors){
        code = code;
        message = errorMessage;
        errors = new HashMap<>();
    }

//    public static ErrorResponse BadRequestError(Map<String, String> errors){
//        return new ErrorResponse(400, "Bad Request Error", errors);
//    }
}

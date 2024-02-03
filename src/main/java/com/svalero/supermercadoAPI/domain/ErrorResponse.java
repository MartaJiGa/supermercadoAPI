package com.svalero.supermercadoAPI.domain;

public class ErrorResponse {
    private int code;
    private String message;

    private ErrorResponse(int errorCode, String errorMessage){
        code = errorCode;
        message = errorMessage;
    }
}

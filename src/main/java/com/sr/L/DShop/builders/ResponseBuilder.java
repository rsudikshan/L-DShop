package com.sr.L.DShop.builders;

import com.sr.L.DShop.models.ResponseModel;

public class ResponseBuilder {

    public static ResponseModel success(String message){
        return ResponseModel.builder()
                .status("success")
                .success(true)
                .message(message)
                .build();
    }

    public static ResponseModel success(String message, Object object){
        return ResponseModel.builder()
                .status("success")
                .success(true)
                .message(message)
                .body(object)
                .build();
    }

    public static ResponseModel failure(String message){
        return ResponseModel.builder()
                .status("failure")
                .success(false)
                .message(message)
                .build();
    }

    public static ResponseModel failure(String message,Object object){
        return ResponseModel.builder()
                .status("failure")
                .success(false)
                .message(message)
                .body(object)
                .build();
    }
}

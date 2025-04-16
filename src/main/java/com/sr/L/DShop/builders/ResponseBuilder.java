package com.sr.L.DShop.builders;

import com.sr.L.DShop.models.ResponseBody;

public class ResponseBuilder {

    public static ResponseBody success(String message){
        return ResponseBody.builder()
                .status("success")
                .success(true)
                .message(message)
                .build();
    }

    public static ResponseBody success(String message,Object object){
        return ResponseBody.builder()
                .status("success")
                .success(true)
                .message(message)
                .body(object)
                .build();
    }

    public static ResponseBody failure(String message){
        return ResponseBody.builder()
                .status("failure")
                .success(false)
                .message(message)
                .build();
    }
}

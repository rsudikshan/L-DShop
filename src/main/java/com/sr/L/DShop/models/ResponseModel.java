package com.sr.L.DShop.models;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class ResponseModel {

    public String message;
    public String status;
    public Boolean success;
    public Object body;

}

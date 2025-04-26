package com.sr.L.DShop.annotations;


import com.sr.L.DShop.constants.Endpoints;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RequestMapping(OrderController.URL)
public @interface OrderController {
    String URL = Endpoints.BASE+"/order";
}

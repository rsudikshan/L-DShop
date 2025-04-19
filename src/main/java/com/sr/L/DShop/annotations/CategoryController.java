package com.sr.L.DShop.annotations;

import com.sr.L.DShop.constants.Endpoints;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping(CategoryController.URL)
public @interface CategoryController {
    String URL = Endpoints.BASE+"/categories";
}

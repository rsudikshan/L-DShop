package com.sr.L.DShop.annotations;

import com.sr.L.DShop.constants.Endpoints;
import com.sr.L.DShop.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping(ProductController.URL)
public @interface ProductController {
    String URL = Endpoints.BASE+"/product";
}

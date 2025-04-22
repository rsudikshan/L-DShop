package com.sr.L.DShop.annotations;

import com.sr.L.DShop.constants.Endpoints;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//visibility
@Target(ElementType.TYPE) //meaning can be used in classes/interfaces
@RestController
@RequestMapping(AuthenticationController.URI)
@CrossOrigin(origins = "*")
public @interface AuthenticationController {
    String URI = Endpoints.BASE+"/auth"; //same as declaring a static final variable thats why we can access it without object
    //must be a compile time constant
}

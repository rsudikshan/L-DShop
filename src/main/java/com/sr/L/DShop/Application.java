package com.sr.L.DShop;

import com.sr.L.DShop.annotations.AuthenticationController;
import com.sr.L.DShop.annotations.CategoryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println(AuthenticationController.URI);
		System.out.println(CategoryController.URL);
	}

	//TODO proper null checks
}

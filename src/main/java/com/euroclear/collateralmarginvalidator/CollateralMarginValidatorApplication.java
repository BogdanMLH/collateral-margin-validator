package com.euroclear.collateralmarginvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CollateralMarginValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollateralMarginValidatorApplication.class, args);
	}

}

package com.allmuniz.api_interest_calculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Interest Calculator",
				description = "Interest calculation service",
				version = "1"
		)
)
public class ApiInterestCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInterestCalculatorApplication.class, args);
	}

}

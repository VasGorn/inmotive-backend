package com.vesmer.inmotive;

import com.vesmer.inmotive.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class InmotiveBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmotiveBackendApplication.class, args);
	}

}

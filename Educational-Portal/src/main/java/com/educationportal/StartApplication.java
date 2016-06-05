package com.educationportal;

import java.io.File;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;


@SpringBootApplication
public class StartApplication {

	public static final String ROOT = "upload-dir";
	private static TemplateEngine templateEngine;

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

	}
	@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			new File(ROOT).mkdir();
		};
	}

}

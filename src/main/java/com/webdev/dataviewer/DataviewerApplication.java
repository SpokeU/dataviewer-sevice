package com.webdev.dataviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
public class DataviewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataviewerApplication.class, args);
	}

}

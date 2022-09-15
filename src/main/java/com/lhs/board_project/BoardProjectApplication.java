package com.lhs.board_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectApplication.class, args);
	}

}

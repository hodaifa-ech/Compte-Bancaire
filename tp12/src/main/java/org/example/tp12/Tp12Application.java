package org.example.tp12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Tp12Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp12Application.class, args);
	}

}

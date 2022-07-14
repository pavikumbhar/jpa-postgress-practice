package com.pavikumbhar.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 * @author pavikumbhar
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class JpaPostgresPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPostgresPracticeApplication.class, args);
	}

}

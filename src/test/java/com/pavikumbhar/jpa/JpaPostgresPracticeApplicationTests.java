package com.pavikumbhar.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
class JpaPostgresPracticeApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}



	//@BeforeEach
	void printApplicationContext() {
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.map(name -> applicationContext.getBean(name).getClass().getName())
				.sorted()
				.forEach(System.out::println);
	}

}

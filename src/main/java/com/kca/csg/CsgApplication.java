package com.kca.csg;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CsgApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CsgApplication.class);
		logger.info("working well");
		SpringApplication.run(CsgApplication.class, args);
	}
}

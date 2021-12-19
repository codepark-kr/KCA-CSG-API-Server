package com.kca.csg;


import com.kca.csg.security.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan(basePackageClasses = { CsgApplication.class, Jsr310Converters.class })
public class CsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsgApplication.class, args);
	}

	@PostConstruct
	void init(){ TimeZone.setDefault(TimeZone.getTimeZone("KST")); }

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(){ return new JwtAuthenticationFilter(); }

	@Bean
	public ModelMapper modelMapper(){ return new ModelMapper(); }

}

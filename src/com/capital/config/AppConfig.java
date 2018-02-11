package com.capital.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(RepositoryConfig.class)
@ComponentScan(value = "com.capital")
public class AppConfig {

	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfig() {
		PropertyPlaceholderConfigurer prop = new PropertyPlaceholderConfigurer();
		prop.setLocation(new ClassPathResource("/ConfigProperties/app.properties"));
		return prop;
	}
}

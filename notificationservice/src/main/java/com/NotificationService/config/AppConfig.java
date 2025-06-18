package com.NotificationService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//RestTemplate config (if using Spring Boot)
@Configuration
public class AppConfig {
 @Bean
 public RestTemplate restTemplate() {
     return new RestTemplate();
 }
}

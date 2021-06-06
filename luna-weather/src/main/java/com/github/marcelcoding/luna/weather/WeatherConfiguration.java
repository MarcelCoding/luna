package com.github.marcelcoding.luna.weather;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfiguration {

  @Bean
  GroupedOpenApi dwdApi() {
    return GroupedOpenApi.builder()
      .group("luna-weather")
      .pathsToMatch("/weather/**")
      .build();
  }
}

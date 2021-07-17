package com.github.marcelcoding.luna.weather;

import com.github.marcelcoding.luna.weather.repository.SensorGroupRepository;
import net.getnova.framework.influx.InfluxProperties;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties(InfluxProperties.class)
@EnableJpaRepositories(basePackageClasses = SensorGroupRepository.class)
public class WeatherConfiguration {

  @Bean
  GroupedOpenApi dwdApi() {
    return GroupedOpenApi.builder()
      .group("luna-weather")
      .pathsToMatch("/weather/**")
      .build();
  }
}

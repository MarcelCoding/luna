package de.m4rc3l.luna.weather;

import de.m4rc3l.luna.weather.repository.SensorGroupRepository;
import de.m4rc3l.nova.influx.InfluxProperties;
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

package com.github.marcelcoding.luna.dvb;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableJpaRepositories(basePackageClasses = GenusRepository.class)
public class DvbConfiguration {

  @Bean
  GroupedOpenApi dvbApi() {
    return GroupedOpenApi.builder()
      .group("luna-dvb")
      .pathsToMatch("/dvb/**")
      .build();
  }
}

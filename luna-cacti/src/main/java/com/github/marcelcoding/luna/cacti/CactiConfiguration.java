package com.github.marcelcoding.luna.cacti;

import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = GenusRepository.class)
public class CactiConfiguration {

  @Bean
  GroupedOpenApi cactiApi() {
    return GroupedOpenApi.builder()
      .group("luna-cacti")
      .pathsToMatch("/cacti/**")
      .build();
  }
}

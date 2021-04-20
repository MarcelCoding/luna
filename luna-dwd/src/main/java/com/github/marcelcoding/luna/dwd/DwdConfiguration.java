package com.github.marcelcoding.luna.dwd;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DwdConfiguration {

  @Bean
  GroupedOpenApi dwdApi() {
    return GroupedOpenApi.builder()
      .group("luna-dwd")
      .pathsToMatch("/dwd/**")
      .build();
  }
}

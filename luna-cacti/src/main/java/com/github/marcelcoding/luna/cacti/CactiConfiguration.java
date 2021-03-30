package com.github.marcelcoding.luna.cacti;

import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = GenusRepository.class)
public class CactiConfiguration {

}

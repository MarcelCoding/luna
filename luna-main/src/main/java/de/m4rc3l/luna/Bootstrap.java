package de.m4rc3l.luna;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import de.m4rc3l.nova.core.NovaBanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Bootstrap {

  public static void main(final String[] args) {
    new SpringApplicationBuilder(Bootstrap.class)
      .banner(new NovaBanner())
      .run(args);
  }

  @Bean
  OpenAPI openApi() {
    return new OpenAPI()
      .info(
        new Info()
          .title("Luna OpenApi Definition")
          .version(Bootstrap.class.getPackage().getImplementationVersion())
      );
  }
}

package com.github.marcelcoding.luna;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.util.stream.Collectors;
import net.getnova.framework.core.GlobalErrorWebExceptionHandler;
import net.getnova.framework.core.NovaBanner;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

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

  @Bean
  @Order(-2)
  public ErrorWebExceptionHandler errorWebExceptionHandler(
    final ErrorAttributes errorAttributes,
    final ResourceProperties resourceProperties,
    final WebProperties webProperties,
    final ApplicationContext applicationContext,
    final ServerProperties serverProperties,
    final ObjectProvider<ViewResolver> viewResolvers,
    final ServerCodecConfigurer serverCodecConfigurer
  ) {
    final AbstractErrorWebExceptionHandler exceptionHandler = new GlobalErrorWebExceptionHandler(
      errorAttributes,
      resourceProperties.hasBeenCustomized() ? resourceProperties : webProperties.getResources(),
      applicationContext,
      serverProperties.getError()
    );
    exceptionHandler.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
    exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
    exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
    return exceptionHandler;
  }
}

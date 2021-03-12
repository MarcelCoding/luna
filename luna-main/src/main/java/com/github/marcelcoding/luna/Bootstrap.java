package com.github.marcelcoding.luna;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.validation.Validator;
import net.getnova.framework.api.data.ApiEndpoint;
import net.getnova.framework.api.executor.ApiExecutor;
import net.getnova.framework.api.parameter.ApiPathVariable;
import net.getnova.framework.api.parameter.ApiQueryVariable;
import net.getnova.framework.api.parameter.ApiRequestData;
import net.getnova.framework.api.parser.ApiParser;
import net.getnova.framework.api.rest.RestApiRouteHandler;
import net.getnova.framework.api.rest.annotation.RestApiController;
import net.getnova.framework.api.rest.parser.RestApiControllerParser;
import net.getnova.framework.api.ws.WebsocketApiRouteHandler;
import net.getnova.framework.api.ws.annotation.WebsocketApiController;
import net.getnova.framework.api.ws.parser.WebsocketApiControllerParser;
import net.getnova.framework.web.server.http.HttpServer;
import net.getnova.framework.web.server.http.route.HttpRoute;
import net.getnova.framework.web.server.http.route.HttpRoutes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.unit.DataSize;
import reactor.netty.http.HttpResources;
import reactor.netty.transport.AddressUtils;

@SpringBootApplication
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Bootstrap {

  public static void main(final String[] args) {
    SpringApplication.run(Bootstrap.class, args);
  }

  @Bean
  ConversionService conversionService() {
    return new DefaultConversionService();
  }

  @Bean
  HttpRoute restApi(
    final ApplicationContext applicationContext,
    final ConversionService conversionService,
    final ObjectMapper objectMapper,
    final Validator validator
  ) {
    final ApiParser parser = new ApiParser(
      new RestApiControllerParser(),
      Set.of(
        new ApiPathVariable.Parser(conversionService),
        new ApiQueryVariable.Parser(conversionService),
        ApiRequestData.Parser.INSTANCE
      )
    );

    final Set<ApiEndpoint> endpoints = parser.parse(
      applicationContext.getBeansWithAnnotation(RestApiController.class).values()
    );

    final ApiExecutor executor = new ApiExecutor(endpoints, validator);

    return HttpRoute.of("api", new RestApiRouteHandler(executor, objectMapper));
  }

  @Bean
  HttpRoute websocketApi(
    final ApplicationContext applicationContext,
    final ConversionService conversionService,
    final ObjectMapper objectMapper,
    final Validator validator
  ) {
    final ApiParser parser = new ApiParser(
      new WebsocketApiControllerParser(),
      Set.of(
        new ApiPathVariable.Parser(conversionService),
        new ApiQueryVariable.Parser(conversionService),
        ApiRequestData.Parser.INSTANCE
      )
    );

    final Set<ApiEndpoint> endpoints = parser.parse(
      applicationContext.getBeansWithAnnotation(WebsocketApiController.class).values()
    );

    final ApiExecutor executor = new ApiExecutor(endpoints, validator);

    return HttpRoute.of("ws", new WebsocketApiRouteHandler(executor, objectMapper));
  }

  @Bean
  HttpResources httpResources() {
    return HttpResources.get();
  }

  @Bean
  HttpServer httpServer(final HttpResources resources, final Set<HttpRoute> routes) {
    final HttpServer httpServer = new HttpServer(
      resources,
      AddressUtils.parseAddress("0.0.0.0", 8080),
      HttpRoutes.of(routes),
      false,
      DataSize.ofKilobytes(5)
    );

    httpServer.start().block();
    return httpServer;
  }
}

package de.m4rc3l.luna.weather;

import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public final class Utils {

  private Utils() {
    throw new UnsupportedOperationException();
  }

  public static <T> Mono<T> handleError(final ClientResponse response, final Class<T> clazz) {
    return response.statusCode().is2xxSuccessful()
      ? response.bodyToMono(clazz)
      : response.createException().flatMap(Mono::error);
  }
}

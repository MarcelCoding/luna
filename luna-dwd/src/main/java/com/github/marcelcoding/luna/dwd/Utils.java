package com.github.marcelcoding.luna.dwd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Slf4j
public final class Utils {

  private Utils() {
    throw new UnsupportedOperationException();
  }

  public static <T> Mono<T> handleError(final ClientResponse response, final Class<T> clazz) {
    if (response.statusCode().equals(HttpStatus.OK)) {
      return response.bodyToMono(clazz);
    }
    else if (response.statusCode().is4xxClientError()) {
      response.createException().subscribe(e -> log.error("Client Error", e));
      return Mono.empty();
    }
    else {
      return response.createException().flatMap(Mono::error);
    }
  }
}

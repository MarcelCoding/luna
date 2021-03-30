package com.github.marcelcoding.luna.cacti;

import java.util.UUID;
import lombok.Getter;

public class NotFoundException extends Exception {

  @Getter
  private final String target;

  public NotFoundException(final String target, final String message) {
    super(message);
    this.target = target;
  }

  public NotFoundException(final UUID target, final String message) {
    super(message);
    this.target = target.toString();
  }

  public NotFoundException(final String target, final String message, final Throwable cause) {
    super(message, cause);
    this.target = target;
  }

//  public ApiResponse toApiResponse() {
//    return ApiResponse.of(HttpResponseStatus.NOT_FOUND, this.target, this.getMessage());
//  }
}

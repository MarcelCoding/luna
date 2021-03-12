package com.github.marcelcoding.luna.dwd.controller;

import com.github.marcelcoding.luna.dwd.dto.PollenData;
import com.github.marcelcoding.luna.dwd.dto.PollenRegion;
import com.github.marcelcoding.luna.dwd.service.PollenService;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.annotation.GetEndpoint;
import net.getnova.framework.api.data.response.ApiResponse;
import net.getnova.framework.api.parameter.ApiPathVariable;
import net.getnova.framework.api.rest.annotation.RestApiController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestApiController("dwd/pollen")
public class PollenController {

  private final PollenService pollenService;

  @GetEndpoint
  public Mono<PollenData> status() {
    return this.pollenService.findData();
  }

  @GetEndpoint("regions")
  public Mono<Set<PollenRegion>> findRegions() {
    return this.pollenService.findRegions();
  }

  @GetEndpoint("regions/{regionId}")
  public Mono<ApiResponse> findPollen(@ApiPathVariable("regionId") final short regionId) {
    return this.pollenService.findPollen(regionId)
      .map(optional ->
        optional.map(ApiResponse::of)
          .orElseGet(() -> ApiResponse.of(HttpResponseStatus.NOT_FOUND, "REGION", "NOT_FOUND"))
      );
  }
}

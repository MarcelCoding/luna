package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.dto.Genus;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.service.GenusService;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.data.response.ApiResponse;
import net.getnova.framework.api.endpoint.DeleteEndpoint;
import net.getnova.framework.api.endpoint.GetEndpoint;
import net.getnova.framework.api.endpoint.PatchEndpoint;
import net.getnova.framework.api.endpoint.PostEndpoint;
import net.getnova.framework.api.endpoint.PutEndpoint;
import net.getnova.framework.api.parameter.ApiPathVariable;
import net.getnova.framework.api.parameter.ApiRequestData;
import net.getnova.framework.api.rest.annotation.RestApiController;

@RequiredArgsConstructor
@RestApiController("/cacti/genera")
public class GenusRestController {

  private final GenusService genusService;

  @GetEndpoint
  public List<GenusModel> findAll() {
    return this.genusService.findAll();
  }

  @GetEndpoint("{id}")
  public ApiResponse findById(@ApiPathVariable("id") final UUID id) {
    return this.genusService.findById(id)
      .map(genus -> ApiResponse.of(genus.toDto()))
      .orElseGet(() -> ApiResponse.of(HttpResponseStatus.NOT_FOUND, "NOT_FOUND", "GENUS"));
  }

  @PostEndpoint
  public Genus post(@ApiRequestData @Valid final Genus genus) {
    return this.genusService.save(new GenusModel(genus.getName())).toDto();
  }

  @PutEndpoint("{id}")
  public ApiResponse put(@ApiPathVariable("id") final UUID id) {
    return ApiResponse.of(HttpResponseStatus.NOT_IMPLEMENTED);
  }

  @PatchEndpoint
  public ApiResponse patch() {
    return ApiResponse.of(HttpResponseStatus.NOT_IMPLEMENTED);
  }

  @DeleteEndpoint("{id}")
  public ApiResponse deleteById(@ApiPathVariable("id") final UUID id) {
    if (this.genusService.deleteById(id)) {
      return ApiResponse.of(HttpResponseStatus.OK);
    }

    return ApiResponse.of(HttpResponseStatus.NOT_FOUND, "GENUS");
  }
}

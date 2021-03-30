package com.github.marcelcoding.luna.dvb.controller;

import com.github.marcelcoding.luna.dvb.dto.Departure;
import com.github.marcelcoding.luna.dvb.dto.RouteChange;
import com.github.marcelcoding.luna.dvb.dto.Stop;
import com.github.marcelcoding.luna.dvb.service.DvbService;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.endpoint.GetEndpoint;
import net.getnova.framework.api.parameter.ApiPathVariable;
import net.getnova.framework.api.rest.annotation.RestApiController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestApiController("dvb")
public class DvbController {

  private final DvbService dvbService;

  @GetEndpoint("stations/{query}")
  public Flux<Stop> findStops(@ApiPathVariable("query") final String query) {
    return this.dvbService.findStops(query);
  }

  @GetEndpoint("departures/{stopId}")
  public Flux<Departure> findDepartures(@ApiPathVariable("stopId") final int stopId) {
    return this.dvbService.findDepartures(stopId);
  }

  @GetEndpoint("routeChanges")
  public Flux<RouteChange> findRouteChanges() {
    return this.dvbService.findRouteChanges();
  }
}

package com.github.marcelcoding.luna.dvb.controller;

import com.github.marcelcoding.luna.dvb.dto.Departure;
import com.github.marcelcoding.luna.dvb.dto.RouteChange;
import com.github.marcelcoding.luna.dvb.dto.Stop;
import com.github.marcelcoding.luna.dvb.service.DvbService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Tag(name = "DVB")
@RequestMapping("dvb")
@RequiredArgsConstructor
public class DvbController {

  private final DvbService dvbService;

  @GetMapping("stations/{query}")
  public Flux<Stop> findStops(@PathVariable("query") final String query) {
    return this.dvbService.findStops(query);
  }

  @GetMapping("departures/{stopId}")
  public Flux<Departure> findDepartures(@PathVariable("stopId") final int stopId) {
    return this.dvbService.findDepartures(stopId);
  }

  @GetMapping("routeChanges")
  public Flux<RouteChange> findRouteChanges() {
    return this.dvbService.findRouteChanges();
  }
}

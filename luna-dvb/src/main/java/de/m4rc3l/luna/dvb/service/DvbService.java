package de.m4rc3l.luna.dvb.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.m4rc3l.luna.dvb.dto.Departure;
import de.m4rc3l.luna.dvb.dto.RouteChange;
import de.m4rc3l.luna.dvb.dto.Stop;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class DvbService {

  private static final String BASE_URL = "https://webapi.vvo-online.de/";
  private final WebClient client;

  public DvbService() {
    this.client = WebClient.create(BASE_URL);
  }

  public Flux<Stop> findStops(final String query) {
    return this.findStops(query, 10);
  }

  public Flux<Stop> findStops(final String query, final int limit) {
    final Map<String, Object> body = Map.of(
      "query", query,
      "limit", limit,
      "stopsOnly", true
    );

    return this.client.post()
      .uri("/tr/pointfinder")
      .bodyValue(body)
      .exchangeToMono(response -> response.bodyToMono(StopsResponse.class))
      .flatMapIterable(StopsResponse::getPoints)
      .map(Stop::of);
  }

  public Flux<Departure> findDepartures(final int stopId) {
    return this.findDepartures(stopId, OffsetDateTime.now(), 20);
  }

  public Flux<Departure> findDepartures(final int stopId, final TemporalAccessor timestamp) {
    return this.findDepartures(stopId, timestamp, 20);
  }

  public Flux<Departure> findDepartures(final int stopId, final int limit) {
    return this.findDepartures(stopId, OffsetDateTime.now(), limit);
  }

  public Flux<Departure> findDepartures(final int stopId, final TemporalAccessor timestamp, final int limit) {
    final Map<String, Object> body = Map.of(
      "stopid", stopId,
      "limit", limit,
      "time", DateTimeFormatter.ISO_DATE_TIME.format(timestamp)
    );

    return this.client.post()
      .uri("/dm")
      .bodyValue(body)
      .exchangeToMono(response -> response.bodyToMono(DeparturesResponse.class))
      .flatMapIterable(DeparturesResponse::getDepartures);
  }

  public Flux<RouteChange> findRouteChanges() {
    return this.client.get()
      .uri("/rc")
      .exchangeToMono(response -> response.bodyToMono(RouteChangesResponse.class))
      .flatMapIterable(RouteChangesResponse::getLines);
  }

  @Data
  private static final class StopsResponse {

    private final List<String> points;

    StopsResponse(
      @JsonProperty("Points") final List<String> points
    ) {
      this.points = points;
    }
  }

  @Data
  private static final class DeparturesResponse {

    private final List<Departure> departures;

    DeparturesResponse(
      @JsonProperty("Departures") final List<Departure> departures
    ) {
      this.departures = departures;
    }
  }

  @Data
  private static final class RouteChangesResponse {

    private final List<RouteChange> lines;

    RouteChangesResponse(
      @JsonProperty("Lines") final List<RouteChange> lines
    ) {
      this.lines = lines;
    }
  }
}

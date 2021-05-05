package com.github.marcelcoding.luna.dvb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.marcelcoding.luna.dvb.dto.Departure;
import com.github.marcelcoding.luna.dvb.dto.RouteChange;
import com.github.marcelcoding.luna.dvb.dto.Stop;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import lombok.SneakyThrows;
import net.getnova.framework.web.client.http.HttpClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.HttpResources;

@Service
public class DvbService {

  private static final JsonNodeFactory NODE_FACTORY = new JsonNodeFactory(false);
  private static final String BASE_URL = "https://webapi.vvo-online.de/";
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private static final AsciiString CONTENT_TYPE = AsciiString.of(HttpHeaderValues.APPLICATION_JSON
    + "; " + HttpHeaderValues.CHARSET + "=" + CHARSET.name());

  private final net.getnova.framework.web.client.http.HttpClient client;

  public DvbService(final ObjectMapper objectMapper, final HttpResources resources) {
    this.client = new HttpClient(resources, BASE_URL, true, objectMapper,
      headers -> headers.add(HttpHeaderNames.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
        .add(HttpHeaderNames.ACCEPT_CHARSET, AsciiString.of(StandardCharsets.UTF_8.toString()))
        .add(HttpHeaderNames.CONTENT_TYPE, CONTENT_TYPE)
    );
  }

  public Flux<Stop> findStops(final String query) {
    return this.findStops(query, 10);
  }

  public Flux<Stop> findStops(final String query, final int limit) {
    final ObjectNode request = NODE_FACTORY.objectNode();
    request.set("query", NODE_FACTORY.textNode(query));
    request.set("limit", NODE_FACTORY.numberNode(limit));
    request.set("stopsOnly", NODE_FACTORY.booleanNode(true));

    return this.client.post(
      "/tr/pointfinder",
      ByteBufMono.fromString(Mono.just(request.toString()), CHARSET, ByteBufAllocator.DEFAULT),
      JsonNode.class
    )
      .flatMapIterable(tree -> tree.<ArrayNode>withArray("Points"))
      .map(node -> Stop.of(node.asText()));
  }

  @SneakyThrows
  public Flux<Departure> findDepartures(final int stopId) {
    return this.findDepartures(stopId, OffsetDateTime.now(), 20);
  }

  @SneakyThrows
  public Flux<Departure> findDepartures(final int stopId, final TemporalAccessor timestamp) {
    return this.findDepartures(stopId, timestamp, 20);
  }

  @SneakyThrows
  public Flux<Departure> findDepartures(final int stopId, final TemporalAccessor timestamp, final int limit) {
    final ObjectNode request = NODE_FACTORY.objectNode();
    request.set("stopid", NODE_FACTORY.numberNode(stopId));
    request.set("limit", NODE_FACTORY.numberNode(limit));
    request.set("time", NODE_FACTORY.textNode(DateTimeFormatter.ISO_DATE_TIME.format(timestamp)));

    return this.client.post(
      "/dm",
      ByteBufMono.fromString(Mono.just(request.toString()), CHARSET, ByteBufAllocator.DEFAULT),
      JsonNode.class
    )
      .flatMapIterable(tree -> tree.<ArrayNode>withArray("Departures"))
      .map(Departure::of);
  }

  public Flux<RouteChange> findRouteChanges() {
    return this.client.get("/rc", JsonNode.class)
      .flatMapIterable(tree -> {
        return tree.<ArrayNode>withArray("Lines");
      })
      .map(RouteChange::of);
  }
}

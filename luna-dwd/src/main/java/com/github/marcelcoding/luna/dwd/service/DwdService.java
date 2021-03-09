package com.github.marcelcoding.luna.dwd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import net.getnova.framework.web.client.http.HttpClient;
import org.springframework.stereotype.Service;
import reactor.netty.http.HttpResources;

@Service
public class DwdService {

  private static final String BASE_URL = "https://opendata.dwd.de/";

  @Getter
  private final net.getnova.framework.web.client.http.HttpClient client;

  public DwdService(final ObjectMapper objectMapper, final HttpResources resources) {
    this.client = new HttpClient(resources, BASE_URL, true, objectMapper,
      headers -> headers.add(HttpHeaderNames.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
        .add(HttpHeaderNames.ACCEPT_CHARSET, AsciiString.of(StandardCharsets.UTF_8.toString()))
    );
  }
}

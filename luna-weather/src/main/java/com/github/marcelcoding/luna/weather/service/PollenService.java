package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.Utils;
import com.github.marcelcoding.luna.weather.dto.Polle;
import com.github.marcelcoding.luna.weather.dto.PollenData;
import com.github.marcelcoding.luna.weather.dto.PollenRegion;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollenService {

  private static final String POLLEN_URI = "/climate_environment/health/alerts/s31fg.json";
  private final DwdService dwdService;

  private PollenData data;

  public Flux<PollenRegion> findRegions() {
    return this.findData().flatMapIterable(PollenData::getRegions);
  }

  public Mono<PollenData> findData() {
    // check if data is outdated
    if (this.data == null /*|| OffsetDateTime.now().isAfter(this.data.getNextUpdate())*/) {
      log.info("Updating pollen data");
      return this.fetchData().doOnNext(data -> this.data = data);
    }

    return Mono.just(this.data);
  }

  private Mono<PollenData> fetchData() {
    return this.dwdService.getClient()
      .get()
      .uri(POLLEN_URI)
      .exchangeToMono(response -> Utils.handleError(response, PollenData.class));
  }

  public Mono<Optional<Map<String, Polle>>> findPollen(final short regionId) {
    return this.findData()
      .map(data -> data.getRegions()
        .stream()
        .filter(region -> region.getId() == regionId)
        .findFirst()
        .map(PollenRegion::getPollen)
      );
  }
}

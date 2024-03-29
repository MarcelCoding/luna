package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.dto.dwd.Polle;
import de.m4rc3l.luna.weather.dto.dwd.PollenData;
import de.m4rc3l.luna.weather.dto.dwd.PollenRegion;
import de.m4rc3l.nova.core.exception.NotFoundException;
import java.time.OffsetDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollenService {

  private final DwdService dwdService;
  private PollenData data;

  public Flux<PollenRegion> findRegions() {
    return this.findData().flatMapIterable(PollenData::getRegions);
  }

  public Mono<PollenData> findData() {
    // check if data is outdated
    if (this.data == null || OffsetDateTime.now().isAfter(this.data.getNextUpdate())) {
      log.info("Updating pollen data");
      return this.dwdService.fetchPollen().doOnNext(data -> this.data = data);
    }

    return Mono.just(this.data);
  }

  public Mono<Map<String, Polle>> findPollen(final short regionId) {
    return this.findData()
      .map(data ->
        data.getRegions()
          .stream()
          .filter(region -> region.getId() == regionId)
          .findAny()
          .orElseThrow(() -> new NotFoundException("REGION"))
          .getPollen()
      );
  }
}

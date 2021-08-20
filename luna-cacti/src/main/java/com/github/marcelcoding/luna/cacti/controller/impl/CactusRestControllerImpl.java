package com.github.marcelcoding.luna.cacti.controller.impl;

import com.github.marcelcoding.luna.cacti.dto.Cactus;
import com.github.marcelcoding.luna.cacti.dto.CactusSmall;
import com.github.marcelcoding.luna.cacti.controller.CactusRestController;
import com.github.marcelcoding.luna.cacti.service.CactusImageService;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import java.io.IOException;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractSmallCrudController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CactusRestControllerImpl
  extends AbstractSmallCrudController<Cactus, CactusSmall, UUID>
  implements CactusRestController {

  private final CactusImageService imageService;

  public CactusRestControllerImpl(
    final CactusService service,
    final CactusImageService imageService
  ) {
    super(service);
    this.imageService = imageService;
  }

  @Override
  @Transactional
  public Mono<Void> postImage(final UUID id, final Flux<FilePart> files) {
    return this.imageService.handleUpload(id, files);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<Resource> getImage(final HttpHeaders headers, final UUID id, final String filename)
    throws IOException {
    final Resource resource = this.imageService.resolveFile(id, filename);

    final long ifModifiedSince = headers.getIfModifiedSince();
    final long lastModified = resource.lastModified();

    if (ifModifiedSince < lastModified) {
      final HttpHeaders newHeaders = new HttpHeaders();
      newHeaders.setLastModified(lastModified);

      return new ResponseEntity<>(resource, newHeaders, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @Override
  @Transactional
  public Mono<Void> deleteImage(final UUID id, final String filename) {
    return this.imageService.deleteFile(id, filename);
  }
}

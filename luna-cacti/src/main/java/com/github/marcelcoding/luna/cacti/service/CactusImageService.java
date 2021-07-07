package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.exception.NotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CactusImageService {

  private static final Path UPLOADS_DIR = Path.of("./cacti-uploads");

  private final CactusRepository cactusRepository;

  public Mono<Void> handleUpload(final UUID cactusId, final Flux<FilePart> files) {
    final CactusModel cactus = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    if (cactus.getImages() == null) {
      cactus.setImages(new HashSet<>());
    }

    return files
      .doOnNext(file -> cactus.getImages().add(file.filename()))
      .doFinally(signal -> this.cactusRepository.save(cactus))
      .flatMap(file -> {
        Path parent = UPLOADS_DIR.resolve(cactusId.toString());
        if (!Files.exists(parent)) {
          try {
            parent = Files.createDirectories(parent);
          }
          catch (IOException e) {
            return Flux.error(e);
          }
        }

        Path path = parent.resolve(file.filename());
        while (Files.exists(path)) {
          path = parent.resolve(file.filename() + "_" + Math.round(Math.random() * 1000));
        }

        return file.transferTo(path);
      })
      .then();
  }

  public Resource resolveFile(final UUID cactusId, final String filename) {
    if (filename.indexOf('/') != -1) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "INVALID_FILE_NAME");
    }

    if (!this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"))
      .getImages()
      .contains(filename)) {
      throw new NotFoundException("FILE_NOT_FOUND");
    }

    return new FileSystemResource(UPLOADS_DIR.resolve(cactusId.toString() + File.separatorChar + filename));
  }

  public Mono<Void> deleteFile(final UUID cactusId, final String filename) {
    if (filename.indexOf('/') != -1) {
      return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "INVALID_FILE_NAME"));
    }

    if (!this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> Exceptions.propagate(new NotFoundException("CACTUS_NOT_FOUND")))
      .getImages()
      .contains(filename)) {
      return Mono.error(new NotFoundException("FILE_NOT_FOUND"));
    }

    // https://projectreactor.io/docs/core/snapshot/reference/#faq.wrap-blocking
    return Mono
      .fromCallable(() -> UPLOADS_DIR.resolve(cactusId.toString() + File.separatorChar + filename).toFile().delete())
      .subscribeOn(Schedulers.boundedElastic())
      .then();
  }
}

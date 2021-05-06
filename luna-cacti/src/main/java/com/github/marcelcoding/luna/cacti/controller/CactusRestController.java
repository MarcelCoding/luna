package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.service.CactusImageService;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Cactus")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/cactus")
public class CactusRestController {

  private final CactusService cactusService;
  private final CactusImageService cactusImageService;

  @GetMapping
  public Set<CactusSmall> findAll() {
    return this.cactusService.findAll();
  }

  @GetMapping("{id}")
  public Cactus findById(@PathVariable("id") final UUID id) {
    return this.cactusService.findById(id)
      .orElseThrow(() -> new NotFoundException(id, "CACTUS_NOT_FOUND"));
  }

  @PostMapping
  public Cactus post(@RequestBody @Valid final Cactus cactus) {
    return this.cactusService.save(cactus);
  }

  @PostMapping("{id}/image")
  public Mono<Void> postImage(
    @PathVariable("id") final UUID id,
    @RequestPart("files[]") final Flux<FilePart> files
  ) {
    return this.cactusImageService.handleUpload(id, files);
  }

  @GetMapping(value = "{id}/image/{filename:.+}", produces = "image/*")
  public Resource getImage(
    @PathVariable("id") final UUID id,
    @PathVariable("filename") final String filename
  ) {
    return this.cactusImageService.resolveFile(id, filename);
  }

  @DeleteMapping("{id}/image/{filename:.+}")
  public Mono<Void> deleteImage(
    @PathVariable("id") final UUID id,
    @PathVariable("filename") final String filename
  ) {
    return this.cactusImageService.deleteFile(id, filename);
  }

  @PutMapping("{id}")
  public Cactus put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Cactus cactus
  ) {
    if (!this.cactusService.exist(id)) {
      throw new NotFoundException(id, "CACTUS_NOT_FOUND");
    }

    return this.cactusService.save(id, cactus);
  }

  @DeleteMapping("{id}")
  public void deleteById(@PathVariable("id") final UUID id) {
    this.cactusService.delete(id);
  }
}

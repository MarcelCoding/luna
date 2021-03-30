package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Genus;
import com.github.marcelcoding.luna.cacti.service.GenusService;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/genus")
public class GenusRestController {

  private final GenusService genusService;

  @GetMapping
  public Collection<Genus> findAll() {
    return this.genusService.findAll();
  }

//  @GetEndpoint("{id}")
//  public ApiResponse findById(@ApiPathVariable("id") final UUID id) {
//    return this.genusService.findById(id)
//      .map(genus -> ApiResponse.of(genus.toDto()))
//      .orElseGet(() -> ApiResponse.of(HttpResponseStatus.NOT_FOUND, "NOT_FOUND", "GENUS"));
//  }

  @PostMapping
  public Genus post(
    @RequestBody @Valid final Genus form
  ) {
    return this.genusService.save(form);
  }

  @PutMapping("{id}")
  public Genus put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Genus genus
  ) {
    if (!this.genusService.exist(id)) {
      throw new RuntimeException(new NotFoundException(id, "GENUS_NOT_FOUND"));
    }

    genus.setId(id);
    return this.genusService.save(genus);
  }

//  @PatchEndpoint
//  public ApiResponse patch() {
//    return ApiResponse.of(HttpResponseStatus.NOT_IMPLEMENTED);
//  }

  @DeleteMapping("{id}")
  public void deleteById(
    @PathVariable("id") final UUID id
  ) {
    try {
      this.genusService.delete(id);
    }
    catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}

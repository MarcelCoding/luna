package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.service.SpecieService;
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
@RequestMapping("/cacti/specie")
public class SpecieRestController {

  private final SpecieService specieService;

  @GetMapping
  public Collection<Specie> findAll() {
    return this.specieService.findAll();
  }

  @PostMapping
  public Specie post(
    @RequestBody @Valid final Specie specie
  ) {
    try {
      return this.specieService.save(specie);
    }
    catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("{id}")
  public Specie put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Specie specie
  ) {
    if (!this.specieService.exist(id)) {
      throw new RuntimeException(new NotFoundException(id, "SPECIE_NOT_FOUND"));
    }

    specie.setId(id);
    try {
      return this.specieService.save(specie);
    }
    catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("{id}")
  public void deleteById(
    @PathVariable("id") final UUID id
  ) {
    try {
      this.specieService.delete(id);
    }
    catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}

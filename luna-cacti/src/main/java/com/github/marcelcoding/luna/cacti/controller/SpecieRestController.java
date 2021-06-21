package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.service.SpecieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Speice")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/specie")
public class SpecieRestController {

  private final SpecieService specieService;

  @GetMapping
  public Set<Specie> findAll() {
    return this.specieService.findAll();
  }

  @PostMapping
  public Specie post(
    @RequestBody @Valid final Specie specie
  ) {
    return this.specieService.save(specie);
  }

  @PutMapping("{id}")
  public Specie put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Specie specie
  ) {
    if (!this.specieService.exist(id)) {
      throw new NotFoundException("SPECIE_NOT_FOUND");
    }

    return this.specieService.save(id, specie);
  }

  @DeleteMapping("{id}")
  public void deleteById(
    @PathVariable("id") final UUID id
  ) {
    this.specieService.delete(id);
  }
}

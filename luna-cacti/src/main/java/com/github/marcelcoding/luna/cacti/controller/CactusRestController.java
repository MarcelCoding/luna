package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
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

@Tag(name = "Cactus")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/cactus")
public class CactusRestController {

  private final CactusService cactusService;

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

  @PutMapping("{id}")
  public Cactus put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Cactus cactus
  ) {
    if (!this.cactusService.exist(id)) {
      throw new NotFoundException(id, "CACTUS_NOT_FOUND");
    }

//    cactus.setId(id);
    return this.cactusService.save(cactus);
  }

  @DeleteMapping("{id}")
  public void deleteById(@PathVariable("id") final UUID id) {
    this.cactusService.delete(id);
  }
}

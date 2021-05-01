package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Form;
import com.github.marcelcoding.luna.cacti.service.FormService;
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

@Tag(name = "Form")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/form")
public class FormRestController {

  private final FormService formService;

  @GetMapping
  public Set<Form> findAll() {
    return this.formService.findAll();
  }

  @PostMapping
  public Form post(@RequestBody @Valid final Form form) {
    return this.formService.save(form);
  }

  @PutMapping("{id}")
  public Form put(
    @PathVariable("id") final UUID id,
    @RequestBody @Valid final Form form
  ) {
    if (!this.formService.exist(id)) {
      throw new NotFoundException(id, "FORM_NOT_FOUND");
    }

    return this.formService.save(id, form);
  }

  @DeleteMapping("{id}")
  public void deleteById(@PathVariable("id") final UUID id) {
    this.formService.delete(id);
  }
}

package de.m4rc3l.luna.cacti.controller;

import de.m4rc3l.luna.cacti.dto.CareGroup;
import de.m4rc3l.luna.cacti.service.CareGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CareGroup")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/care-group")
public class CareGroupRestController {

  private final CareGroupService careGroupService;

  @GetMapping
  public Collection<CareGroup> findAll() {
    return this.careGroupService.findAll();
  }
}

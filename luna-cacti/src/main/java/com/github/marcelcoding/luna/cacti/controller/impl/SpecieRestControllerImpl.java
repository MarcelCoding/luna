package com.github.marcelcoding.luna.cacti.controller.impl;

import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.controller.SpecieRestController;
import com.github.marcelcoding.luna.cacti.service.SpecieService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecieRestControllerImpl extends AbstractCrudController<Specie, UUID> implements SpecieRestController {

  public SpecieRestControllerImpl(
    final SpecieService service
  ) {
    super(service);
  }
}

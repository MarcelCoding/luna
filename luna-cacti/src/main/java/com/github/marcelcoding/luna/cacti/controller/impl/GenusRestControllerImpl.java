package com.github.marcelcoding.luna.cacti.controller.impl;

import com.github.marcelcoding.luna.cacti.api.Genus;
import com.github.marcelcoding.luna.cacti.controller.GenusRestController;
import com.github.marcelcoding.luna.cacti.service.GenusService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenusRestControllerImpl extends AbstractCrudController<Genus, UUID> implements GenusRestController {

  public GenusRestControllerImpl(
    final GenusService service
  ) {
    super(service);
  }
}

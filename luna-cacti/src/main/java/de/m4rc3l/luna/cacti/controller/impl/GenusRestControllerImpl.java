package de.m4rc3l.luna.cacti.controller.impl;

import de.m4rc3l.luna.cacti.dto.Genus;
import de.m4rc3l.luna.cacti.controller.GenusRestController;
import de.m4rc3l.luna.cacti.service.GenusService;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenusRestControllerImpl extends AbstractCrudController<Genus, UUID> implements GenusRestController {

  public GenusRestControllerImpl(
    final GenusService service
  ) {
    super(service);
  }
}

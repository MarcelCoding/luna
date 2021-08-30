package de.m4rc3l.luna.cacti.controller.impl;

import de.m4rc3l.luna.cacti.dto.Specie;
import de.m4rc3l.luna.cacti.controller.SpecieRestController;
import de.m4rc3l.luna.cacti.service.SpecieService;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecieRestControllerImpl extends AbstractCrudController<Specie, UUID> implements SpecieRestController {

  public SpecieRestControllerImpl(
    final SpecieService service
  ) {
    super(service);
  }
}

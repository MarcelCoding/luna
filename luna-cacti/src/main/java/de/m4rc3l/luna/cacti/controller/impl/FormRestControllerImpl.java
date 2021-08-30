package de.m4rc3l.luna.cacti.controller.impl;

import de.m4rc3l.luna.cacti.dto.Form;
import de.m4rc3l.luna.cacti.controller.FormRestController;
import de.m4rc3l.luna.cacti.service.FormService;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormRestControllerImpl extends AbstractCrudController<Form, UUID> implements FormRestController {

  public FormRestControllerImpl(
    final FormService service
  ) {
    super(service);
  }
}

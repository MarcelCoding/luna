package com.github.marcelcoding.luna.cacti.controller.impl;

import com.github.marcelcoding.luna.cacti.dto.Form;
import com.github.marcelcoding.luna.cacti.controller.FormRestController;
import com.github.marcelcoding.luna.cacti.service.FormService;
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

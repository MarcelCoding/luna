package com.github.marcelcoding.luna.cacti.service.impl;

import com.github.marcelcoding.luna.cacti.api.Form;
import com.github.marcelcoding.luna.cacti.converter.FormConverter;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.repository.FormRepository;
import com.github.marcelcoding.luna.cacti.service.FormService;
import java.util.UUID;
import net.getnova.framework.core.service.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl
  extends AbstractCommonIdCrudService<Form, UUID, FormModel>
  implements FormService {

  public FormServiceImpl(
    final FormRepository repository,
    final FormConverter converter
  ) {
    super("FORM", repository, converter);
  }
}

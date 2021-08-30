package de.m4rc3l.luna.cacti.service.impl;

import de.m4rc3l.luna.cacti.converter.FormConverter;
import de.m4rc3l.luna.cacti.dto.Form;
import de.m4rc3l.luna.cacti.model.FormModel;
import de.m4rc3l.luna.cacti.repository.FormRepository;
import de.m4rc3l.luna.cacti.service.FormService;
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

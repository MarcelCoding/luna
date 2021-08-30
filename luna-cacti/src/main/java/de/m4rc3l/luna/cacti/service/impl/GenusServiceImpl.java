package de.m4rc3l.luna.cacti.service.impl;

import de.m4rc3l.luna.cacti.converter.GenusConverter;
import de.m4rc3l.luna.cacti.dto.Genus;
import de.m4rc3l.luna.cacti.model.GenusModel;
import de.m4rc3l.luna.cacti.repository.GenusRepository;
import de.m4rc3l.luna.cacti.service.GenusService;
import java.util.UUID;
import net.getnova.framework.core.service.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class GenusServiceImpl
  extends AbstractCommonIdCrudService<Genus, UUID, GenusModel>
  implements GenusService {

  public GenusServiceImpl(
    final GenusRepository repository,
    final GenusConverter converter
  ) {
    super("GENUS", repository, converter);
  }
}

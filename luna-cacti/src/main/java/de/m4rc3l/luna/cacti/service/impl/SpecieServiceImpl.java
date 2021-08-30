package de.m4rc3l.luna.cacti.service.impl;

import de.m4rc3l.luna.cacti.converter.SpecieConverter;
import de.m4rc3l.luna.cacti.dto.Specie;
import de.m4rc3l.luna.cacti.model.SpecieModel;
import de.m4rc3l.luna.cacti.repository.SpecieRepository;
import de.m4rc3l.luna.cacti.service.SpecieService;
import java.util.UUID;
import net.getnova.framework.core.service.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class SpecieServiceImpl
  extends AbstractCommonIdCrudService<Specie, UUID, SpecieModel>
  implements SpecieService {

  public SpecieServiceImpl(
    final SpecieRepository repository,
    final SpecieConverter converter
  ) {
    super("SPECIE", repository, converter);
  }
}

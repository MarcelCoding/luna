package de.m4rc3l.luna.cacti.service.impl;

import de.m4rc3l.luna.cacti.converter.CactusConverter;
import de.m4rc3l.luna.cacti.converter.CactusSmallConverter;
import de.m4rc3l.luna.cacti.dto.Cactus;
import de.m4rc3l.luna.cacti.dto.CactusSmall;
import de.m4rc3l.luna.cacti.model.CactusModel;
import de.m4rc3l.luna.cacti.model.CactusSmallModel;
import de.m4rc3l.luna.cacti.repository.CactusRepository;
import de.m4rc3l.luna.cacti.repository.CactusSmallRepository;
import de.m4rc3l.luna.cacti.service.CactusService;
import java.util.UUID;
import de.m4rc3l.nova.core.service.AbstractSmallCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class CactusServiceImpl
  extends AbstractSmallCommonIdCrudService<Cactus, CactusSmall, UUID, CactusModel, CactusSmallModel>
  implements CactusService {

  public CactusServiceImpl(
    final CactusRepository repository,
    final CactusSmallRepository smallRepository,
    final CactusConverter converter,
    final CactusSmallConverter smallConverter
  ) {
    super("CACTUS", repository, smallRepository, converter, smallConverter);
  }
}

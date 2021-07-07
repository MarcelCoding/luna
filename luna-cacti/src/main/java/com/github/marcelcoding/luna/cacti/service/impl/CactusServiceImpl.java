package com.github.marcelcoding.luna.cacti.service.impl;

import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.converter.CactusConverter;
import com.github.marcelcoding.luna.cacti.converter.CactusSmallConverter;
import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.model.CactusSmallModel;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import com.github.marcelcoding.luna.cacti.repository.CactusSmallRepository;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import java.util.UUID;
import net.getnova.framework.core.service.AbstractSmallCommonIdCrudService;
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

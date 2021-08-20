package com.github.marcelcoding.luna.cacti.service.impl;

import com.github.marcelcoding.luna.cacti.dto.Genus;
import com.github.marcelcoding.luna.cacti.converter.GenusConverter;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import com.github.marcelcoding.luna.cacti.service.GenusService;
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

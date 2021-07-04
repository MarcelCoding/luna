package com.github.marcelcoding.luna.cacti.service.impl;

import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.converter.SpecieConverter;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.SpecieRepository;
import com.github.marcelcoding.luna.cacti.service.SpecieService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCommonIdCrudService;
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

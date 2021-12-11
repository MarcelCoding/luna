package de.m4rc3l.luna.cacti.service;

import de.m4rc3l.luna.cacti.dto.Cactus;
import de.m4rc3l.luna.cacti.dto.CactusSmall;
import java.util.UUID;
import de.m4rc3l.nova.core.service.SmallCrudService;

public interface CactusService extends SmallCrudService<Cactus, CactusSmall, UUID> {

}

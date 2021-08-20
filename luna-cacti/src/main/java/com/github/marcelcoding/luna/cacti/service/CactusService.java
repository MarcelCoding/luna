package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.dto.Cactus;
import com.github.marcelcoding.luna.cacti.dto.CactusSmall;
import java.util.UUID;
import net.getnova.framework.core.service.SmallCrudService;

public interface CactusService extends SmallCrudService<Cactus, CactusSmall, UUID> {

}

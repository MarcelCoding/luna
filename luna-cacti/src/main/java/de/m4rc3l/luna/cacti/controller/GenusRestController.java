package de.m4rc3l.luna.cacti.controller;

import de.m4rc3l.luna.cacti.dto.Genus;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import net.getnova.framework.core.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Genus")
@RequestMapping("/cacti/genus")
public interface GenusRestController extends CrudController<Genus, UUID> {

}

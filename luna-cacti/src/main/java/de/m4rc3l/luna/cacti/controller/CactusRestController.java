package de.m4rc3l.luna.cacti.controller;

import de.m4rc3l.luna.cacti.dto.Cactus;
import de.m4rc3l.luna.cacti.dto.CactusSmall;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.UUID;
import net.getnova.framework.core.controller.SmallCrudController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Cactus")
@RequestMapping("/cacti/cactus")
public interface CactusRestController extends SmallCrudController<Cactus, CactusSmall, UUID> {

  @PostMapping("{id}/image")
  Mono<Void> postImage(
    @PathVariable("id") UUID id,
    @RequestPart("files") Flux<FilePart> files
  );

  @GetMapping(value = "{id}/image/{filename:.+}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<Resource> getImage(
    @RequestHeader HttpHeaders headers,
    @PathVariable("id") UUID id,
    @PathVariable("filename") String filename
  ) throws IOException;

  @DeleteMapping("{id}/image/{filename:.+}")
  Mono<Void> deleteImage(
    @PathVariable("id") UUID id,
    @PathVariable("filename") String filename
  );
}

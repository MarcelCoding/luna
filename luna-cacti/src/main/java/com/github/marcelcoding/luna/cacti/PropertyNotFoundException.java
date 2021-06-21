package com.github.marcelcoding.luna.cacti;

import net.getnova.framework.core.NotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;

public class PropertyNotFoundException extends NotFoundException {

  public PropertyNotFoundException(final PropertyReferenceException cause) {
    super(cause.getPropertyName() + "_FOUND", cause);
  }
}

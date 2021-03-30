package com.github.marcelcoding.luna.cacti;

import org.springframework.data.mapping.PropertyReferenceException;

public class PropertyNotFoundException extends NotFoundException {

  public PropertyNotFoundException(final PropertyReferenceException cause) {
    super(cause.getPropertyName(), "PROPERTY_NOT_FOUND", cause);
  }
}

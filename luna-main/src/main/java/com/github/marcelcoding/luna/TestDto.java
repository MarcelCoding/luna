package com.github.marcelcoding.luna;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestDto {

  @Email
  @NotNull
  private final String email;

  public TestDto(
    @JsonProperty("email") final String email
  ) {
    this.email = email;
  }
}

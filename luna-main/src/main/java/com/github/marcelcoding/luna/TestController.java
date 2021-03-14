package com.github.marcelcoding.luna;

import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.endpoint.GetEndpoint;
import net.getnova.framework.api.endpoint.PostEndpoint;
import net.getnova.framework.api.parameter.ApiPathVariable;
import net.getnova.framework.api.parameter.ApiRequestData;
import net.getnova.framework.api.ws.annotation.WebsocketApiController;

@RequiredArgsConstructor
@WebsocketApiController("/hello")
public class TestController {

  @GetEndpoint
  public String hello() {
    return "Hello!";
  }

  @GetEndpoint("{name}")
  public String hello(@ApiPathVariable("name") final String name) {
    return "Hello " + name;
  }

  @PostEndpoint
  public String post(@ApiRequestData final TestDto test) {
    return test.getEmail();
  }
}

package io.spinnaker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spinnaker.api.model.Application;
import io.spinnaker.api.model.metadata.ApplicationMetadata;
import io.spinnaker.api.retrofit.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Component
public class SpinnakerApi {
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  ApplicationService applicationService;

  public Application getApplication(String applicationName) {
    try {
      Response<Map> response = applicationService.getApplication(applicationName).execute();
      return new Application(objectMapper.convertValue(response.body().get("attributes"), ApplicationMetadata.class));
    } catch (IOException e) {
      throw new RuntimeException(String.format("Unable to fetch application '%s'", applicationName), e);
    }
  }
}

package io.spinnaker.api.model;

import io.spinnaker.api.model.metadata.ApplicationMetadata;

public class Application {
  public final ApplicationMetadata applicationMetadata;

  public Application(ApplicationMetadata applicationMetadata) {
    this.applicationMetadata = applicationMetadata;
  }
}

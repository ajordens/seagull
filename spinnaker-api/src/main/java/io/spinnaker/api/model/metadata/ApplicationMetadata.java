package io.spinnaker.api.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationMetadata {
  @JsonProperty
  public String name;

  @JsonProperty
  public String description;

  @JsonProperty
  public String email;

  @JsonProperty
  public String lastModifiedBy;

  @JsonProperty
  public String updateTs;
}

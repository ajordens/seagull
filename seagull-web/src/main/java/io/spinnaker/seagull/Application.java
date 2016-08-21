package io.spinnaker.seagull;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Map;

@SpringBootApplication(scanBasePackages = "io.spinnaker.config")
public class Application extends SpringBootServletInitializer {
  private static final Map<String, Object> DEFAULT_PROPS = ImmutableMap.<String, Object>builder()
      .put("spring.config.location", "${user.home}/.spinnaker/")
      .put("spring.config.name", "seagull")
      .put("spring.profiles.active", "local")
      .build();

  public static void main(String[] args) {
    new SpringApplicationBuilder().properties(DEFAULT_PROPS).sources(Application.class).run(args);
  }

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.properties(DEFAULT_PROPS).sources(Application.class);
  }
}

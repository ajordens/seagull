package io.spinnaker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.spinnaker.seagull", "io.spinnaker.slack"})
public class SeagullConfig {
}

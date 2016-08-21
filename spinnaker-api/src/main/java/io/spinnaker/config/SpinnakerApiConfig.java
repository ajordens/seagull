package io.spinnaker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spinnaker.api.retrofit.ApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@ComponentScan({"io.spinnaker.api"})
public class SpinnakerApiConfig {
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public ApplicationService applicationService(Retrofit retrofit) {
    return retrofit.create(ApplicationService.class);
  }

  @Bean
  public Retrofit retrofit(@Value("${services.gate.baseUrl}") String gateBaseUrl) {
    return new Retrofit.Builder()
        .baseUrl(gateBaseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .build();
  }
}
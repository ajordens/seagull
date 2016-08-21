package io.spinnaker.slack.config;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import io.spinnaker.slack.events.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class SlackConfig {
  @Value("${slack.apiToken}")
  String apiToken;

  @Bean
  SlackSession slackSession(Collection<Event> events) throws IOException {
    SlackSession session = SlackSessionFactory.createWebSocketSlackSession(apiToken);
    session.connect();

    events.forEach(event -> event.register(session));

    return session;
  }
}
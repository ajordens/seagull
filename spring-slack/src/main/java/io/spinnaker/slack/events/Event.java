package io.spinnaker.slack.events;

import com.ullink.slack.simpleslackapi.SlackSession;

public interface Event {
  void register(SlackSession slackSession);
}
package io.spinnaker.seagull.commands;

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public interface Command {
  boolean supports(String commandName);
  void handle(String args, SlackMessagePosted slackMessage);
}

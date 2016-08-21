package io.spinnaker.seagull.events;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import io.spinnaker.seagull.commands.Command;
import io.spinnaker.slack.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandEvent implements Event, SlackMessagePostedListener {
  private static Pattern COMMAND_PATTERN = Pattern.compile("!(\\w[\\w|-]+)(.*)");

  @Autowired
  Collection<Command> commands;

  @Override
  public void onEvent(SlackMessagePosted event, SlackSession session) {
    boolean isDirectMessage = event.getChannel().getName().equals(event.getChannel().getId());
    boolean isCommand = event.getMessageContent().startsWith("!");

    if (isDirectMessage && isCommand) {
      String message = event.getMessageContent();
      Matcher m = COMMAND_PATTERN.matcher(message);
      if (m.matches()) {
        commands
            .stream()
            .filter(c -> c.supports(m.group(1)))
            .forEach(c -> c.handle(m.group(2), event));
      };
    } else {
      System.out.println(String.format("Unsupported event: %s", event));
    }
  }

  @Override
  public void register(SlackSession slackSession) {
    slackSession.addMessagePostedListener(this);
  }
}

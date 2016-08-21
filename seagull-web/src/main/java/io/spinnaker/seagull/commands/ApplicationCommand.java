package io.spinnaker.seagull.commands;

import com.ullink.slack.simpleslackapi.SlackAttachment;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import io.spinnaker.api.SpinnakerApi;
import io.spinnaker.api.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCommand implements Command {
  @Autowired
  SpinnakerApi spinnakerApi;

  @Autowired
  SlackSession slackSession;

  @Override
  public boolean supports(String commandName) {
    return "application".equalsIgnoreCase(commandName);
  }

  @Override
  public void handle(String args, SlackMessagePosted slackMessage) {
    Application application = spinnakerApi.getApplication(args);

    SlackAttachment slackAttachment = new SlackAttachment(
        null,
        null,
        null,
        null
    );
    slackAttachment.addField("Name", application.applicationMetadata.name, false);
    slackAttachment.addField("Email", application.applicationMetadata.email, true);
    slackAttachment.addField("Last Modified By", application.applicationMetadata.lastModifiedBy, true);
    slackAttachment.addField("Description", application.applicationMetadata.description, false);

    slackSession.sendMessage(slackMessage.getChannel(), null, slackAttachment);
  }
}

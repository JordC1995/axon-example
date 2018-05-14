package org.axonframework.sample.axonmsg.Aggregates;
/**
 * @author Jordan
 *
 * Messages aggregate class definining a means of handling incoming commands related to the
 * messaes entity.
 * */
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.sample.axonmsg.Commands.CreateMessageCommand;
import org.axonframework.sample.axonmsg.Commands.MarkReadMessageCommand;
import org.axonframework.sample.axonmsg.Events.MessageCreatedEvent;
import org.axonframework.sample.axonmsg.Events.MessageReadEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@NoArgsConstructor
public class MessagesAggegate {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public MessagesAggegate(CreateMessageCommand command) {
        apply(new MessageCreatedEvent(command.getId(), command.getText()));
    }

    @EventHandler
    public void on(MessageCreatedEvent event) {
        this.id = event.getId();
    }

    @CommandHandler
    public void markRead(MarkReadMessageCommand command) {
        apply(new MessageReadEvent(id));
    }

    @EventHandler
    public void on(MessageReadEvent event) {
        this.id = event.getId();
    }
}

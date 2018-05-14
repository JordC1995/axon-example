/**
 * @author Jordan
 *
 * Event handler class defines a way of handling the result of event generation.
 *
 * Event information could be place into a register from this class.
 * */
package org.axonframework.sample.axonmsg.EventHandlers;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.sample.axonmsg.Events.MessageCreatedEvent;
import org.axonframework.sample.axonmsg.Events.MessageReadEvent;

public class MessagesEventHandler {

    public MessagesEventHandler() {

    }

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        System.out.println("Message received: " + event.getText() + " (" + event.getId() + ")");
    }

    @EventHandler
    public void handle(MessageReadEvent event) {
        System.out.println("Message read: " + event.getId());
    }
}

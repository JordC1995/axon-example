
/**
 * @author Jordan
 *
 * Model class which defines the business rules related to a the message created event.
 * */
package org.axonframework.sample.axonmsg.Events;

public class MessageCreatedEvent {

    private String id;
    private String text;

    public MessageCreatedEvent(String id, String text) {

        this.id = id;
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

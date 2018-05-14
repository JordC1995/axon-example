/**
 * @author Jordan
 *
 * Model class which defines the business rules related to a the message read event.
 * */
package org.axonframework.sample.axonmsg.Events;

public class MessageReadEvent {

    private String id;

    public MessageReadEvent(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

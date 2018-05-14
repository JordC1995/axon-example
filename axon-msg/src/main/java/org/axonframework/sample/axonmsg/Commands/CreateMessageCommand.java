/**
 * @author Jordan
 *
 * Model class which defines the business rules & constraints
 * related to Message creation functionality
 * */

package org.axonframework.sample.axonmsg.Commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateMessageCommand {

    @TargetAggregateIdentifier
    private String id;
    private String text;

    public CreateMessageCommand(String id, String text) {
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

/**
 * @author Jordan
 *
 * Model class which defines the business rules & constraints
 * related to Message reading functionality
 * */
package org.axonframework.sample.axonmsg.Commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class MarkReadMessageCommand {

    @TargetAggregateIdentifier
    private String id;

    public MarkReadMessageCommand(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

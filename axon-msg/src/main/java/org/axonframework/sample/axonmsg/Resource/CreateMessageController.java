package org.axonframework.sample.axonmsg.Resource;

import org.axonframework.sample.axonmsg.EventHandlers.MessagesEventHandler;
import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.sample.axonmsg.Aggregates.MessagesAggegate;
import org.axonframework.sample.axonmsg.Commands.CreateMessageCommand;
import org.axonframework.sample.axonmsg.Commands.MarkReadMessageCommand;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
/**
 * @author Jordan
 *
 * Controller class exposing REST endpoints which fire off commands subsequently generating corresponing events.
 * */
@RestController
public class CreateMessageController {

    private CommandBus commandBus;
    private CommandGateway commandGateway;

    private EventStore eventStore;
    private EventSourcingRepository<MessagesAggegate> repository;

    private AggregateAnnotationCommandHandler<MessagesAggegate> handler;
    private AnnotationEventListenerAdapter annotationEventListenerAdapter;


    public CreateMessageController() {
        this.commandBus = new SimpleCommandBus();
        this.commandGateway = new DefaultCommandGateway(commandBus);

        this.eventStore = new EmbeddedEventStore(new InMemoryEventStorageEngine());
        this.repository =  new EventSourcingRepository<>(MessagesAggegate.class, eventStore);

        handler = new AggregateAnnotationCommandHandler<MessagesAggegate>(
                MessagesAggegate.class, repository);

        handler.subscribe(commandBus);

        annotationEventListenerAdapter = new AnnotationEventListenerAdapter(new MessagesEventHandler());
        eventStore.subscribe(eventMessages -> eventMessages.forEach(e -> {
            try {
                annotationEventListenerAdapter.handle(e);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }));
    }

    @RequestMapping(value = "/CreateMessage", method = RequestMethod.POST)
    public void createMessage(@RequestBody Map<String, String> body) {

        String id = UUID.randomUUID().toString();

        commandGateway.send(new CreateMessageCommand(id, body.get("message")));
    }

    @RequestMapping(value = "/ReadMessage/{id}", method = RequestMethod.GET)
    public String readMessage(@PathVariable String id) {
        commandGateway.send(new MarkReadMessageCommand(id));

        return id + ": Marked as read.";
    }
}

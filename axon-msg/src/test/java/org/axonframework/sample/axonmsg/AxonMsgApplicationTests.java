package org.axonframework.sample.axonmsg;

import org.axonframework.sample.axonmsg.Aggregates.MessagesAggegate;
import org.axonframework.sample.axonmsg.Commands.CreateMessageCommand;
import org.axonframework.sample.axonmsg.Commands.MarkReadMessageCommand;
import org.axonframework.sample.axonmsg.Events.MessageCreatedEvent;
import org.axonframework.sample.axonmsg.Events.MessageReadEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxonMsgApplicationTests {

	private FixtureConfiguration<MessagesAggegate> fixture;

	@Before
	public void setup() throws Exception {
		fixture = new AggregateTestFixture<MessagesAggegate>(MessagesAggegate.class);
	}

	@Test
	public void createMessageTest() {
		String eventText = "Hello how was your day?";
		String id = UUID.randomUUID().toString();

		fixture.given()
				.when(new CreateMessageCommand(id, eventText))
				.expectEvents(new MessageCreatedEvent(id, eventText));
	}

	@Test
	public void readMessageTest() {
		String id = UUID.randomUUID().toString();
		String eventText = "Hello how was your day?";

		fixture.given(new MessageCreatedEvent(id, eventText))
				.when(new MarkReadMessageCommand(id))
				.expectEvents(new MessageReadEvent(id));
	}

}

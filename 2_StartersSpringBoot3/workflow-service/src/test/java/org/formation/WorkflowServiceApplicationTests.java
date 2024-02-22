package org.formation;

import org.assertj.core.api.Assertions;
import org.formation.domain.State;
import org.formation.domain.Transition;
import org.formation.domain.Workflow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static java.lang.Thread.sleep;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class WorkflowServiceApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Container
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@Test
	void contextLoads() {
	}

	@Test
	public void testPost() throws Exception {

		State state = State.builder().name("CREATED").build();;
		Transition transition = Transition.builder().action("CREATE").sourceState("").targetState("CREATED").build();
		Workflow workflow = Workflow.builder().id("TEST").name("TEST").states(Collections.singletonList(state)).transitions(Collections.singletonList(transition)).build();

		webClient.post().uri("/workflows")
				.body(Mono.just(workflow) , Workflow.class)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Workflow.class)
				.consumeWith(response -> {
					Workflow responseWorkflow = response.getResponseBody();
					Assertions.assertThat(responseWorkflow).isNotNull();
					Assertions.assertThat(responseWorkflow.getId()).isEqualTo("TEST");
				});;
	}


}

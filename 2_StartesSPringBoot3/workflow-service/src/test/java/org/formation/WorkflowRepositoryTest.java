package org.formation;

import org.formation.domain.Workflow;
import org.formation.domain.repository.WorkflowRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@Testcontainers
public class WorkflowRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    WorkflowRepository workflowRepository;

    @BeforeEach
    public void setUp() {
        Workflow test = Workflow.builder().name("TEST").build();
        Workflow test2 = Workflow.builder().name("TEST2").build();
        workflowRepository.save(test).block();
        workflowRepository.save(test2).block();
    }
    @Test
    public void testFindByName() {
        Flux<Workflow> result = workflowRepository.findByName("TEST");

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }
}

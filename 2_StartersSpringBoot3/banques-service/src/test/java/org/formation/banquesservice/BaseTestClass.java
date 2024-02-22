package org.formation.banquesservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.formation.banquesservice.config.WorkflowConfig;
import org.formation.banquesservice.service.event.DomainEvent;
import org.formation.banquesservice.web.BanqueController;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {"workflow-service.url=http://localhost:${wiremock.server.port}"})
@DirtiesContext
@Testcontainers
@AutoConfigureWireMock(port = 0)
public class BaseTestClass {

    @Autowired
    private BanqueController banqueController;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    WorkflowConfig workflowConfig;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.1-alpine");

    @Container
    @ServiceConnection
    static KafkaContainer kafkaContainer = new KafkaContainer("5.5.0");
    @BeforeEach
    public void setup() throws JsonProcessingException {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(banqueController).addPlaceholderValue("api.version", "1.0");
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        DomainEvent domainEvent = new DomainEvent(1,"","CREATED");

        stubFor(post(urlEqualTo("/workflows/"+workflowConfig.getWorkflowId()+"?action=CREATE"))
                .willReturn(aResponse()
                        .withBody(mapper.writeValueAsString(domainEvent))
                        .withHeader("Content-Type", "application/json")
                        .withFixedDelay(1000)));
    }
}

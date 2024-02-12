package org.formation.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GatewayApplication.class)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = {"org.formation:banques-service:0.0.1-SNAPSHOT:stubs:8080"})
public class GatewayRouteTests {

    @Autowired
    private WebTestClient webTestClient;



    @Test
    public void routingToBanqueServiceIsSuccessful() throws Exception {
        String json="{\n" +
                "  \"nomBanque\": \"MoneyO\",\n" +
                "  \"bic\": \"CMCIFR2A\",\n" +
                "  \"codeBanque\": 12,\n" +
                "  \"numero\": 0,\n" +
                "  \"rue\": \"string\",\n" +
                "  \"ville\": \"string\",\n" +
                "  \"codePostal\": 0,\n" +
                "  \"pays\": \"string\"\n" +
                "}";
        webTestClient.post().uri("/api/banques")
                .header("Content-Type", "application/json")
                .bodyValue(json)
                .exchange()
                .expectStatus().isCreated();


    }
}

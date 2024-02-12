package org.formation.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.gateway.web.BanqueDto;
import org.formation.gateway.web.GatewayController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"backend.banque-root-uri=http://localhost:${wiremock.server.port}",
                "backend.workflow-root-uri=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
public class GatewayCompositionTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testComposition() throws Exception {

        BanqueDto dto = new BanqueDto();
        dto.setName("MoneyO");
        dto.setStatus("CREATED");
        List<BanqueDto> list = Collections.singletonList(dto);

        stubFor(get(urlEqualTo(GatewayController.GET_BANQUES))
                .willReturn(aResponse()
                        .withBody(mapper.writeValueAsString(list))
                        .withHeader("Content-Type", "application/json")));
        stubFor(get(urlEqualTo("/workflows/BANQUES/actions?state=CREATED"))
                .willReturn(aResponse()
                        .withBody("[\"ACCEPT\",\"REJECT\"]")
                        .withHeader("Content-Type", "application/json")
                        .withFixedDelay(1000)));


        webClient.get().uri("/todo").exchange().expectStatus().isOk();
    }
}

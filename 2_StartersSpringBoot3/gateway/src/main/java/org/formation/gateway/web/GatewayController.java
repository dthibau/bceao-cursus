package org.formation.gateway.web;

import org.formation.gateway.BackendConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
public class GatewayController {

    public final static String GET_BANQUES = "/api/1.0/banques";
    public final static String GET_ACTIONS = "/workflows/{id}/actions?state={state}";
    final WebClient banqueClient;

    final WebClient workflowClient;



    public GatewayController(WebClient.Builder webClientBuilder, BackendConfig backendConfig) {
        this.banqueClient = webClientBuilder.baseUrl(backendConfig.getBanqueRootUri()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        this.workflowClient = webClientBuilder.baseUrl(backendConfig.getWorkflowRootUri()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
    @GetMapping("/todo")
    public Flux<BanqueDto> findTodos() {
        Flux<BanqueDto> banques = findBanques();

        return banques.flatMap(b -> findActions(b));

    }

    private Flux<BanqueDto> findBanques() {
        return banqueClient.get().uri(GET_BANQUES).retrieve().bodyToFlux(BanqueDto.class);
    }

    private Mono<BanqueDto> findActions(BanqueDto dto) {
        return workflowClient.get().uri(GET_ACTIONS, "BANQUES",dto.getStatus()).retrieve().bodyToMono(String[].class).map(actions -> {
            dto.setActions(Arrays.asList(actions));
            return dto;
        });
    }
}

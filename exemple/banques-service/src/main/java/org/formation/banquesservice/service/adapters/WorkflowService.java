package org.formation.banquesservice.service.adapters;

import org.formation.banquesservice.config.WorkflowConfig;
import org.formation.banquesservice.service.event.DomainEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkflowService {

    RestTemplate restTemplate;

    private final String ACTION_URL = "/workflows/{id}?action={action}";

    public WorkflowService(RestTemplateBuilder restTemplateBuilder, WorkflowConfig config) {
        this.restTemplate = restTemplateBuilder.rootUri(config.url).build();
    }

    public DomainEvent action(String id, String action) {
        return this.restTemplate.postForObject(ACTION_URL, null, DomainEvent.class, id, action);

    }
}

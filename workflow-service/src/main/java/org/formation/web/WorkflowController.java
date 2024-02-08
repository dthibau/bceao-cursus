package org.formation.web;

import org.formation.domain.Transition;
import org.formation.domain.Workflow;
import org.formation.service.WorkflowService;
import org.formation.service.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

    @Autowired
    WorkflowService workflowService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Workflow> create(@RequestBody Workflow workflow) {
        return workflowService.create(workflow);
    }
    @GetMapping
    public Flux<Workflow> findAll() {
        return workflowService.findAll();
    }
    @GetMapping("/{processId}")
    public Mono<List<Transition>> getAvailableTransitions(@PathVariable String processId, @RequestParam(required = false) String state) {
        if ( state == null )
            state = "";
        return workflowService.findAvailableTransitions(processId, state);
    }

    @PostMapping("/{processId}")
    public Mono<DomainEvent> action(@PathVariable String processId, @RequestParam String action) {
        return workflowService.action(processId, action);
    }
}

package org.formation.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Obtenir la liste des workflow avec exemples")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des workflows",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Workflow.class),
                            examples = {
                                    @ExampleObject(name = "example1", value = "\n" +
                                            "[{\"id\":\"BANQUE\",\"name\":\"Workflow validation établissement\",\"states\":[{\"name\":\"CREATED\"}],\"transitions\":[{\"action\":\"CREATE\",\"sourceState\":\"\",\"targetState\":\"CREATED\"}]}]"),
                                    @ExampleObject(name = "example2", value = "\n" +
                                            "[{\"id\":\"TRANSACTION\",\"name\":\"Workflow validation établissement\",\"states\":[{\"name\":\"CREATED\"}],\"transitions\":[{\"action\":\"CREATE\",\"sourceState\":\"\",\"targetState\":\"CREATED\"}]}]")
                            }
                    )
            )
    })
    @GetMapping
    public Flux<Workflow> findAll() {
        return workflowService.findAll();
    }
    @GetMapping("/{processId}/actions")
    public Mono<List<String>> getAvailableActions(@PathVariable String processId, @RequestParam(required = false) String state) {
        if ( state == null )
            state = "";
        return workflowService.findAvailableTransitions(processId, state).map(l -> {
            return l.stream().map(t -> t.getAction()).toList();
        });
    }

    @PostMapping("/{processId}")
    public Mono<DomainEvent> action(@PathVariable String processId, @RequestParam String action) {
        return workflowService.action(processId, action);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return workflowService.deleteById(id);
    }
}

package org.formation.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class Workflow {
    @Schema(description = "The id of the workflow", example = "BANQUES")
    private String id;
    @Schema(description = "A human-readable name of the workflow")
    private String name;
    private List<State> states;
    private List<Transition> transitions;

    public Transition findTransitionByName(String name) {
        return transitions.stream()
                .filter(t -> t.getAction().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Transition> findAvailableTransitions(String stateName) {
          return transitions.stream()
                    .filter(t -> t.getSourceState().equals(stateName))
                    .toList();
    }
}

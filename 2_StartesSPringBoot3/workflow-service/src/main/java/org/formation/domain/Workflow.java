package org.formation.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workflow {
    @Schema(description = "The id of the workflow", example = "BANQUES")
    @Id
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

package org.formation.domain;

import lombok.Data;

import java.util.List;

@Data
public class Workflow {
    private String id;
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

package org.formation.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Transition {
    @Schema(description = "The action that triggers the transition", examples = {"SUBMIT","APPROVE","REJECT"})
    String action;
    String sourceState;
    String targetState;
}

package org.formation.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transition {
    @Schema(description = "The action that triggers the transition", examples = {"SUBMIT","APPROVE","REJECT"})
    String action;
    String sourceState;
    String targetState;
}

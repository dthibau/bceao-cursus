package org.formation.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class State {
    @Schema(description = "The id of the state", examples = {"CREATED","PENDING","APPROVED","REJECTED"})
    String name;
}

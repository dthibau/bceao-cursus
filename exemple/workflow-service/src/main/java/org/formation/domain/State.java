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
public class State {
    @Schema(description = "The id of the state", examples = {"CREATED","PENDING","APPROVED","REJECTED"})
    String name;
}

package org.formation.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomainEvent {

    protected long id;

    protected String oldStatus;

    protected String newStatus;
}

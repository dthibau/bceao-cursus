package org.formation.banquesservice.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@Data
@AllArgsConstructor
@RegisterReflectionForBinding
public class DomainEvent {

    protected long id;

    protected String oldStatus;

    protected String newStatus;
}

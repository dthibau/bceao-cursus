package org.formation.banquesservice.service.adapters;

import org.formation.banquesservice.service.event.DomainEvent;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {


    public DomainEvent action(Long id, String action) {
        // Call rest service
        if ( action.equals("CREATE") ) {
            return new DomainEvent(1, "", "CREATED");
        } else {
            throw new IllegalArgumentException("Action not supported");
        }

    }
}

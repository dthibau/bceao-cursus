package org.formation.banquesservice.service.adapters;

import lombok.extern.java.Log;
import org.formation.banquesservice.service.event.DomainEvent;
import org.springframework.stereotype.Service;

@Service
@Log
public class EventService {


    public void publishEvent(DomainEvent domainEvent) {
        log.info("Publishing event " + domainEvent);
        // kafkaTemplate.send("banque", domainEvent);


    }
}

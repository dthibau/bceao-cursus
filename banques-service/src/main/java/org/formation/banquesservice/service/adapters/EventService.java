package org.formation.banquesservice.service.adapters;

import lombok.extern.java.Log;
import org.formation.banquesservice.config.KafkaConfig;
import org.formation.banquesservice.service.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log
public class EventService {

    @Autowired
    KafkaConfig kafkaConfig;
    @Autowired
    KafkaTemplate<Long, DomainEvent> kafkaTemplate;

    public void publishEvent(DomainEvent domainEvent) {
        log.info("Publishing event " + domainEvent);
         kafkaTemplate.send(kafkaConfig.domainTopic, domainEvent.getId(), domainEvent);


    }
}

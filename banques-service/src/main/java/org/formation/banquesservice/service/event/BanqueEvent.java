package org.formation.banquesservice.service.event;

import lombok.Data;
import org.formation.banquesservice.domain.Banque;

@Data
public class BanqueEvent extends DomainEvent {

        private Banque banque;

        public BanqueEvent(DomainEvent domainEvent, Banque banque) {
            super(domainEvent.id, domainEvent.oldStatus, domainEvent.newStatus);
            this.banque = banque;
        }


}

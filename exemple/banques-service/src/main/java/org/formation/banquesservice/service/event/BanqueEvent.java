package org.formation.banquesservice.service.event;

import lombok.Data;
import org.formation.banquesservice.domain.Banque;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@Data
@RegisterReflectionForBinding
public class BanqueEvent extends DomainEvent {

        private Banque banque;

        public BanqueEvent(DomainEvent domainEvent, Banque banque) {
            super(banque.getId(), domainEvent.oldStatus, domainEvent.newStatus);
            this.banque = banque;
        }


}

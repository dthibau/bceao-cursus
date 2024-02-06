package org.formation.banquesservice.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.formation.banquesservice.domain.Banque;
import org.formation.banquesservice.domain.repository.BanqueRepository;
import org.formation.banquesservice.dto.BanqueDto;
import org.formation.banquesservice.dto.CreateBanqueDto;
import org.formation.banquesservice.service.adapters.EventService;
import org.formation.banquesservice.service.adapters.WorkflowService;
import org.formation.banquesservice.service.event.BanqueEvent;
import org.formation.banquesservice.service.event.DomainEvent;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log
public class BanqueService {

    final BanqueRepository banqueRepository;
    final EventService eventService;
    final WorkflowService workflowService;

    public BanqueService(BanqueRepository banqueRepository, EventService eventService, WorkflowService workflowService) {
        this.banqueRepository = banqueRepository;
        this.eventService = eventService;
        this.workflowService = workflowService;
    }
    public BanqueDto createBanque(CreateBanqueDto dto) {
        log.info("Creating a new banque");
        Banque banque = Banque.createBanque(dto);
        banque = banqueRepository.save(banque);
        DomainEvent event = workflowService.action(banque.getId(), "CREATE");
        eventService.publishEvent(new BanqueEvent(event, banque));

        return new BanqueDto(banque);
    }
}

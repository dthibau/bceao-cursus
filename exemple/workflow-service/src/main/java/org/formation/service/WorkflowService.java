package org.formation.service;

import org.formation.domain.Transition;
import org.formation.domain.Workflow;
import org.formation.domain.repository.ProcessRepository;
import org.formation.service.event.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WorkflowService {
    final ProcessRepository processRepository;
    public WorkflowService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }
    public Mono<DomainEvent> action(String idProcess, String action) {
        return processRepository.findById(idProcess).
                switchIfEmpty(Mono.error(new IllegalArgumentException("Process not found")))
                .map(w -> {
                    return w.findTransitionByName(action);
                }).switchIfEmpty(Mono.error(new IllegalArgumentException("Transition not found")))
                .map(t -> {
                    return new DomainEvent(1, t.getSourceState(), t.getTargetState());
                });
    }

    public Mono<List<Transition>> findAvailableTransitions(String idProcess, String state) {
        return processRepository.findById(idProcess).
                switchIfEmpty(Mono.error(new IllegalArgumentException("Process not found")))
                .map(w -> {
                    return w.findAvailableTransitions(state);
                });
    }
    public Mono<Workflow> create(Workflow workflow) {
        return processRepository.save(workflow);
    }

    public Flux<Workflow> findAll() {
        return processRepository.findAll();
    }

    public Mono<Void> deleteById(String id) {
        return processRepository.deleteById(id);
    }
}

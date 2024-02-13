package org.formation.domain.repository;

import org.formation.domain.Workflow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkflowRepository extends ReactiveMongoRepository<Workflow, String>{

    Flux<Workflow> findByName(String name);

}

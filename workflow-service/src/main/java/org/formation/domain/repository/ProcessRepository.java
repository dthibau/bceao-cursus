package org.formation.domain.repository;

import org.formation.domain.Workflow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProcessRepository extends ReactiveMongoRepository<Workflow, String>{
}

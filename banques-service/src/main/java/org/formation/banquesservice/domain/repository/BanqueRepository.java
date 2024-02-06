package org.formation.banquesservice.domain.repository;

import org.formation.banquesservice.domain.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanqueRepository extends JpaRepository<Banque, Long> {
}

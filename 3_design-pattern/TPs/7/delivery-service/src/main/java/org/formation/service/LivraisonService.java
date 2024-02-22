package org.formation.service;

import java.time.Instant;

import org.formation.domain.Livraison;
import org.formation.domain.Status;
import org.formation.domain.repository.LivraisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LivraisonService {

	private final LivraisonRepository livraisonRepository;

	public LivraisonService(LivraisonRepository livraisonRepository) {
		this.livraisonRepository = livraisonRepository;
	}

	public Livraison createDelivery(Long orderId, Long ticketId) {
		Livraison l = new Livraison();
		l.setCreationDate(Instant.now());
		l.setOrderId(orderId);
		l.setTicketId(ticketId);
		l.setStatus(Status.CREE);

		return livraisonRepository.save(l);
	}

}

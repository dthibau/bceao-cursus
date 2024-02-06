package org.formation.banquesservice.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.formation.banquesservice.dto.CreateBanqueDto;

@Entity
@Data
public class Banque {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomBanque;
    private String bic;
    private int codeBanque;

    @Embedded
    Adresse adresse;

    // Comming from a worflow service
    String status;

    @Version
    private int version;

    // Need for JPA
    public Banque() {
        super();
    }

    public static Banque createBanque(CreateBanqueDto dto) {
        Banque banque = new Banque();
        banque.nomBanque = dto.nomBanque();
        banque.bic = dto.bic();
        banque.codeBanque = dto.codeBanque();
        banque.adresse = new Adresse(dto.numero(), dto.rue(), dto.ville(), dto.codePostal(), dto.pays());
        banque.status = "CREATED";

        return banque;
    }
}

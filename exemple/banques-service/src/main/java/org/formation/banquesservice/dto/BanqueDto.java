package org.formation.banquesservice.dto;

import lombok.Data;
import org.formation.banquesservice.domain.Adresse;
import org.formation.banquesservice.domain.Banque;

@Data
public class BanqueDto {
    private Long id;
    private String nomBanque;
    private String bic;
    private int codeBanque;
    String status;

    private Adresse adresse;

    public BanqueDto() {
        super();
    }
    public BanqueDto(Banque banque) {
        this.id = banque.getId();
        this.nomBanque = banque.getNomBanque();
        this.bic = banque.getBic();
        this.codeBanque = banque.getCodeBanque();
        this.status = banque.getStatus();
        this.adresse = banque.getAdresse();
    }
}

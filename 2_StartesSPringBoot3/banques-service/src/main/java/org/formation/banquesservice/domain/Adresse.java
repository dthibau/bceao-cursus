package org.formation.banquesservice.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse {

    private int numero;

    private String rue;

    private String ville;

    private int codePostal;

    private String pays;
}

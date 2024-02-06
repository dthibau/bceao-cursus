package org.formation.banquesservice.dto;

import jakarta.validation.constraints.Pattern;

public record CreateBanqueDto(
        String nomBanque,
        @Pattern(regexp = "[A-Za-z]{6}[A-Za-z0-9]{2}([A-Za-z0-9]{3})?", message = "BIC format is not valid")
        String bic,
        int codeBanque,
        int numero,
        String rue,
        String ville,
        int codePostal,
        String pays
) {
}

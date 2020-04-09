package be.ucm.projetrecrutementapi.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangementProprietaireFormulaire {
    private Long ancienProprietaire;
    private Long nouveauProprietaire;
    private Long projet;
    private boolean response;
}

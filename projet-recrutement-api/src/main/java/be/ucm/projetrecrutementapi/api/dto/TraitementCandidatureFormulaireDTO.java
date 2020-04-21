package be.ucm.projetrecrutementapi.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TraitementCandidatureFormulaireDTO {
    private Long candidatureId;
    private boolean accepte;
}

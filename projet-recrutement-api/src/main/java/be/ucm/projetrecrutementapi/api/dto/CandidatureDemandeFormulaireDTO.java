package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CandidatureDemandeFormulaireDTO {
    @NotNull
    @PositiveOrZero
    @Max(168)
    private int nbHeuresSemaine;
    @NotNull
    private Long utilisateurId;
    @NotNull
    private Long projetId;
    private Set<TechnologieDTO> technologiesSouhaitees = new HashSet<>();
}

package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CandidatureFormulaireDTO {
    @NotNull
    @Positive
    @Max(168)
    private int nbHeuresSemaine;
    @NotNull
    @Positive
    private Long utilisateurId;
    @NotNull
    @Positive
    private Long projetId;
    @Size(min=1)
    private Set<Long> technologiesSouhaitees = new HashSet<>();
}

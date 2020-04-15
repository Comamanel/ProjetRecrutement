package be.ucm.projetrecrutementapi.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnnulationParticipationProjetDTO {
    private Long utilisateurId;
    private Long projetId;
    private boolean annulationActeeEnDB;
    private boolean necessitePasserLeRoleDeProprietaire;
}

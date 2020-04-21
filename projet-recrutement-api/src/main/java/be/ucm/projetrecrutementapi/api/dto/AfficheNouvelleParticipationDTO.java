package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfficheNouvelleParticipationDTO {
    private Long participationId;
    private Long utilisateurId;
    private Long projetId;
    private boolean actif;
    private boolean proprio;

    public AfficheNouvelleParticipationDTO(){}
    public AfficheNouvelleParticipationDTO (Participation_Projet pp){
        this.participationId = pp.getId();
        this.utilisateurId = pp.getUtilisateur().getId();
        this.projetId = pp.getProjet().getId();
        this.actif = pp.isActif();
        this.proprio = pp.isProprio();
    }
}

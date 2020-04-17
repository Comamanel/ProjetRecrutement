package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToutesInfo_Participation_Projet_DTO {
    private Long id;
    private Utilisateur utilisateur;
    private Projet projet;
    private boolean actif;
    private boolean proprio;

    public Participation_Projet toEntity(){
        Participation_Projet participationProjet = new Participation_Projet();
        participationProjet.setId(this.id);
        participationProjet.setUtilisateur(this.utilisateur);
        participationProjet.setProprio(this.proprio);
        participationProjet.setActif(this.actif);
        participationProjet.setProjet(this.projet);
        return participationProjet;
    }

    public ToutesInfo_Participation_Projet_DTO(){}
    public ToutesInfo_Participation_Projet_DTO(Participation_Projet pp){
        this.id = pp.getId();
        this.utilisateur = pp.getUtilisateur();
        this.projet = pp.getProjet();
        this.actif = pp.isActif();
        this.proprio = pp.isProprio();
    }
}

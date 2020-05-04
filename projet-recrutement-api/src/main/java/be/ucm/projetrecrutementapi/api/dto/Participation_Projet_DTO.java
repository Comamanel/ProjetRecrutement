package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participation_Projet_DTO {
    private Long id;
    private ProjetDTO projet;
    private boolean actif;
    private boolean proprio;

    public Participation_Projet toEntity(){
        Participation_Projet participationProjet = new Participation_Projet();
        participationProjet.setId(this.id);
        participationProjet.setProprio(this.proprio);
        participationProjet.setActif(this.actif);
        participationProjet.setProjet(this.projet.toEntity());
        return participationProjet;
    }

    public Participation_Projet_DTO(){}
    public Participation_Projet_DTO(Participation_Projet pp){
        this.id = pp.getId();
        this.projet = new ProjetDTO(pp.getProjet());
        this.actif = pp.isActif();
        this.proprio = pp.isProprio();
    }
}

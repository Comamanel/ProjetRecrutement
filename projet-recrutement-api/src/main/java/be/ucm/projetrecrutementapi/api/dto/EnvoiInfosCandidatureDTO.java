package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvoiInfosCandidatureDTO {
    private Utilisateur utilisateur;
    private ProjetDTO projet;
}

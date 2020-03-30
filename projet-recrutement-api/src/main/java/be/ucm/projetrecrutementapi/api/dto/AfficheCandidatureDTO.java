package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AfficheCandidatureDTO {
    private Long id;
    private int nbHeuresSemaine;
    private EtatCandidature statut;
    private AfficheUtilisateurDTO utilisateur;
    private AfficheProjetDTO projet;
    private Set<TechnologieDTO> technologiesSouhaitee = new HashSet<>();

    public AfficheCandidatureDTO(Candidature candidature){
        this.id = candidature.getId();
        this.nbHeuresSemaine = candidature.getNbHeuresSemaine();
        this.statut = candidature.getStatut();
        this.utilisateur = new AfficheUtilisateurDTO(candidature.getUtilisateur());
        this.projet = new AfficheProjetDTO(candidature.getProjet());
        this.technologiesSouhaitee = candidature.getTechnologieSouhaitee().stream().map(TechnologieDTO::new).collect(Collectors.toSet());
    }

}

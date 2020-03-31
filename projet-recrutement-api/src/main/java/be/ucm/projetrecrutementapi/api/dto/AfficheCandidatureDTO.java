package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AfficheCandidatureDTO {
    private Long id;
    private int nbHeuresSemaine;
    private EtatCandidature statut;
    private AfficheUtilisateurDTO utilisateur;
    private AfficheProjetDTO projet;
    private Set<TechnologieDTO> technologiesSouhaitees = new HashSet<>();

    public AfficheCandidatureDTO(Candidature candidature){
        this.id = candidature.getId();
        this.nbHeuresSemaine = candidature.getNbHeuresSemaine();
        this.statut = candidature.getStatut();
        this.utilisateur = new AfficheUtilisateurDTO(candidature.getUtilisateur());
        this.projet = new AfficheProjetDTO(candidature.getProjet());
        this.technologiesSouhaitees = candidature.getTechnologieSouhaitee().stream().map(TechnologieDTO::new).collect(Collectors.toSet());
    }

}

package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AfficheProjetDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateDebut;
    private TypeProjet typeProjet;
    private int maxParticipants;
    private LocalDate dateFin;
    private int tpsTravailHebdo;
    private EtatProjet statut;

    private Set<MaitriseDTO> maitrisesDemandees = new HashSet<>();

    public AfficheProjetDTO(Projet projet){
        this.id = projet.getId();
        this.name = projet.getNom();
        this.description = projet.getDescription();
        this.dateDebut = projet.getDateDebut();
        this.typeProjet = projet.getTypeProjet();
        this.maxParticipants = projet.getMaxParticipants();
        this.dateFin = projet.getDateFin();
        this.tpsTravailHebdo = projet.getTpsTravailHebdo();
        this.statut = projet.getStatut();

        projet.getMaitrisesDemandees().forEach(m -> this.maitrisesDemandees.add(new MaitriseDTO(m)));
    }
}

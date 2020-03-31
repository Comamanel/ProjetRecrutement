package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProjetDTO {

    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeProjet typeProjet;
    private int maxParticipants;
    private int tpsTravailHebdo;
    private EtatProjet statut;
    private List<MaitriseDTO> maitrises = new ArrayList<>();

    public Projet toEntity(){
        Projet nouveauProjet = new Projet();
        nouveauProjet.setNom(this.nom);
        nouveauProjet.setDescription(this.description);
        nouveauProjet.setDateDebut(this.dateDebut);
        nouveauProjet.setDateFin(this.dateFin);
        nouveauProjet.setTypeProjet(this.typeProjet);
        nouveauProjet.setMaxParticipants(this.maxParticipants);
        nouveauProjet.setTpsTravailHebdo(this.tpsTravailHebdo);
        nouveauProjet.setStatut(EtatProjet.ACT);
        nouveauProjet.setMaitrisesDemandees(this.getMaitrises().stream().map(MaitriseDTO::toEntity).collect(Collectors.toSet()));
        return nouveauProjet;
    }

    public ProjetDTO(Projet projet) {
        this.id = projet.getId();
        this.nom = projet.getNom();
        this.description = projet.getDescription();
        this.dateDebut = projet.getDateDebut();
        this.dateFin = projet.getDateFin();
        this.typeProjet = projet.getTypeProjet();
        this.maxParticipants = projet.getMaxParticipants();
        this.tpsTravailHebdo = projet.getTpsTravailHebdo();
        this.statut = projet.getStatut();
        this.maitrises = projet.getMaitrisesDemandees().stream().map(MaitriseDTO::new).collect(Collectors.toList());
    }
}

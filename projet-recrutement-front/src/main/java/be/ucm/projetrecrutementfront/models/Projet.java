package be.ucm.projetrecrutementfront.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Projet {

    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private String typeProjet;
    private int maxParticipants;
    private LocalDate dateFin;
    private int tpsTravailHebdo;
    private String statut;
    private int nbParticipants;
    //private Set<Maitrise> maitrisesDemandees = new HashSet<>();
    //private Set<Candidature> candidatures = new HashSet<>();
    //private Set<ProfilType> profilsType = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTypeProjet() {
        return typeProjet;
    }

    public void setTypeProjet(String typeProjet) {
        this.typeProjet = typeProjet;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getTpsTravailHebdo() {
        return tpsTravailHebdo;
    }

    public void setTpsTravailHebdo(int tpsTravailHebdo) {
        this.tpsTravailHebdo = tpsTravailHebdo;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public Projet() {};
    public Projet(Long id, String nom, String description, LocalDate dateDebut, String typeProjet, int maxParticipants, LocalDate dateFin, int tpsTravailHebdo, String statut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.typeProjet = typeProjet;
        this.maxParticipants = maxParticipants;
        this.dateFin = dateFin;
        this.tpsTravailHebdo = tpsTravailHebdo;
        this.statut = statut;
    };
}

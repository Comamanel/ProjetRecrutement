package be.ucm.projetrecrutementfront.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Projet {
    private Long id;
    private Long adminId;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private String dateDebutString;
    private LocalDate dateFin;
    private String dateFinString;
    private String typeProjet;
    private int maxParticipants;
    private int tpsTravailHebdo;
    private String statut;
    private int nbParticipants;
    private Set<Maitrise> maitrises = new HashSet<>();
    private Set<Utilisateur> utilActifs = new HashSet<>();
    private Set<Utilisateur> utilNonActifs = new HashSet<>();
    //private Set<Candidature> candidatures = new HashSet<>();
    //private Set<ProfilType> profilsType = new HashSet<>();

    public Projet() {
    }

    public Projet(Long id, String nom, String description, LocalDate dateDebut, String typeProjet, int maxParticipants, LocalDate dateFin, int tpsTravailHebdo, String statut) {
        this.id = id;
        this.adminId = null;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.typeProjet = typeProjet;
        this.maxParticipants = maxParticipants;
        this.dateFin = dateFin;
        this.tpsTravailHebdo = tpsTravailHebdo;
        this.statut = statut;
    }

    public Projet(Long id, String nom, String description, LocalDate dateDebut, String dateDebutString, LocalDate dateFin, String dateFinString, String typeProjet, int maxParticipants, int tpsTravailHebdo, String statut) {
        this.id = id;
        this.adminId = null;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateDebutString = dateDebutString;
        this.dateFin = dateFin;
        this.dateFinString = dateFinString;
        this.typeProjet = typeProjet;
        this.maxParticipants = maxParticipants;
        this.tpsTravailHebdo = tpsTravailHebdo;
        this.statut = statut;
    }

    public Projet(Long id, String nom, String description, LocalDate dateDebut, String dateDebutString, LocalDate dateFin, String dateFinString, String typeProjet, int maxParticipants, int tpsTravailHebdo, String statut, Set<Maitrise> maitrises) {
        this.id = id;
        this.adminId = null;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateDebutString = dateDebutString;
        this.dateFin = dateFin;
        this.dateFinString = dateFinString;
        this.typeProjet = typeProjet;
        this.maxParticipants = maxParticipants;
        this.tpsTravailHebdo = tpsTravailHebdo;
        this.statut = statut;
        this.maitrises = maitrises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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

    public String getDateDebutString() {
        return dateDebutString;
    }

    public void setDateDebutString(String dateDebutString) {
        this.dateDebutString = dateDebutString;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateFinString() {
        return dateFinString;
    }

    public void setDateFinString(String dateFinString) {
        this.dateFinString = dateFinString;
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

    public Set<Maitrise> getMaitrises() {
        return maitrises;
    }

    public void setMaitrises(Set<Maitrise> maitrises) {
        this.maitrises = maitrises;
    }

    public Set<Utilisateur> getUtilActifs() {
        return utilActifs;
    }

    public void setUtilActifs(Set<Utilisateur> utilActifs) {
        this.utilActifs = utilActifs;
    }

    public Set<Utilisateur> getUtilNonActifs() {
        return utilNonActifs;
    }

    public void setUtilNonActifs(Set<Utilisateur> utilNonActifs) {
        this.utilNonActifs = utilNonActifs;
    }
}

package be.ucm.projetrecrutementfront.models;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur {
    private Long id;
    private String email;
    private String pseudo;
    private LocalDate dateDeNaissance;
    private String dateDeNaissanceFormattee;
    private String nom;
    private String prenom;
    private String infosSupp;
    private String numTel;
    private String pays;
    private String lienGit;
    private String photoProfil;
    private String cvDoc;
    private Group group;
    private Set<Maitrise> maitrises;
    private Set<Role> roles;
    private Set<Participation_Projet> projetsParticipes;
    private Set<Projet> projetsCrees;

    public Utilisateur() {
        this.maitrises = new HashSet<>();
        this.roles = new HashSet<>();
        this.projetsParticipes = new HashSet<>();
        this.projetsCrees = new HashSet<>();
    }


    public Utilisateur(Long id, String email, String pseudo, LocalDate dateDeNaissance, String nom, String prenom, String infosSupp, String numTel, String pays, String lienGit, String photoProfil, String cvDoc, Group group) {
        this();
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.dateDeNaissance = dateDeNaissance;
        this.dateDeNaissanceFormattee = dateDeNaissance.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.nom = nom;
        this.prenom = prenom;
        this.infosSupp = infosSupp;
        this.numTel = numTel;
        this.pays = pays;
        this.lienGit = lienGit;
        this.photoProfil = photoProfil;
        this.cvDoc = cvDoc;
        this.group = group;
    }

    public Utilisateur(Long id, String email, String pseudo, LocalDate dateDeNaissance, String dateDeNaissanceFormattee, String nom, String prenom, String infosSupp, String numTel, String pays, String lienGit, String photoProfil, String cvDoc, Group group, Set<Maitrise> maitrises, Set<Role> roles, Set<Participation_Projet> projetsParticipes, Set<Projet> projetsCrees) {
        this();
        this.maitrises = maitrises;
        this.roles = roles;
        this.projetsParticipes = projetsParticipes;
        this.projetsCrees = projetsCrees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        this.dateDeNaissanceFormattee = dateDeNaissance.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDateDeNaissanceFormattee() {
        return dateDeNaissanceFormattee;
    }

    public void setDateDeNaissanceFormattee(String dateDeNaissanceFormattee) {
        this.dateDeNaissanceFormattee = dateDeNaissanceFormattee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getInfosSupp() {
        return infosSupp;
    }

    public void setInfosSupp(String infosSupp) {
        this.infosSupp = infosSupp;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLienGit() {
        return lienGit;
    }

    public void setLienGit(String lienGit) {
        this.lienGit = lienGit;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getCvDoc() {
        return cvDoc;
    }

    public void setCvDoc(String cvDoc) {
        this.cvDoc = cvDoc;
    }

    public Set<Maitrise> getMaitrises() {
        return maitrises;
    }

    public void setMaitrises(Set<Maitrise> maitrises) {
        this.maitrises = maitrises;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Participation_Projet> getProjetsParticipes() {
        return projetsParticipes;
    }

    public void setProjetsParticipes(Set<Participation_Projet> projetsParticipes) {
        this.projetsParticipes = projetsParticipes;
    }

    public Set<Projet> getProjetsCrees() {
        return projetsCrees;
    }

    public void setProjetsCrees(Set<Projet> projetsCrees) {
        this.projetsCrees = projetsCrees;
    }
}

package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of={"id", "email", "motDePasse", "pseudo", "dateDeNaissance"})
@ToString
public class Utilisateur implements Serializable {

    @Id
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private String pseudo;
    @Column(nullable = false)
    private LocalDate dateDeNaissance;
    private String nom;
    private String prenom;
    private String infoSupp;
    private String numTel;
    private String pays;
    private String lienGit;
    private String photoProfil;
    private String cvDoc;

    @ManyToOne(targetEntity = Group.class, cascade = CascadeType.PERSIST)
    private Group group;


    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.PERSIST)
    private Set<Role> roles;
    @OneToMany(targetEntity = Participation_Projet.class, mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
    private Set<Participation_Projet> projetsParticipes;
    /*
    private Set<Projet> projetsCrees;
    private Set<Maitrise> maitrises;
     */

    public Utilisateur(){
        this.roles = new HashSet<>();
        this.projetsParticipes = new HashSet<>();
        /*
        this.projetsCrees = new HashSet<>();
        this.maitrises = new HashSet<>();
         */
    }

    public Utilisateur(Long id, String email, String motDePasse, String pseudo, LocalDate dateDeNaissance, String nom, String prenom, String infoSupp, String numTel, String pays, String lienGit, String photoProfil, String cvDoc, Group group) {
        this();
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.pseudo = pseudo;
        this.dateDeNaissance = dateDeNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.infoSupp = infoSupp;
        this.numTel = numTel;
        this.pays = pays;
        this.lienGit = lienGit;
        this.photoProfil = photoProfil;
        this.cvDoc = cvDoc;
        this.group = group;
    }
}

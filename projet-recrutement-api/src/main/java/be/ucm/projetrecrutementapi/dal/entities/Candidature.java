package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "candidature_id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private int nbHeuresSemaine;
    @Column(nullable = false)
    private EtatCandidature statut;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Projet_Id")
    private Projet projet;

    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<Technologie> technologieSouhaitee = new HashSet<>();

}

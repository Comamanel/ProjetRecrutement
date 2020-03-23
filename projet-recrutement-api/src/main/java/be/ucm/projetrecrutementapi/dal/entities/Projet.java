package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projet {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_projet", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false)
    private LocalDate dateDebut;
    @Column(nullable = false)
    private TypeProjet typeProjet;
    @Column(nullable = false)
    private int maxParticipants;
    private LocalDate dateFin;
    private int tpsTravailHebdo;
    @Column(nullable = false)
    private EtatProjet statut;

    @ManyToMany (cascade = CascadeType.PERSIST)
    private Set<Maitrise> maitrisesDemandees = new HashSet<>();

    @OneToMany (cascade = CascadeType.PERSIST)
    private Set<Candidature> candidatures = new HashSet<>();

}

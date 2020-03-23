package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id", "actif", "proprio", "utilisateur"/*, "projet"*/ })
public class Participation_Projet implements Serializable {
    @Id
    private Long id;
    @ManyToOne(targetEntity = Utilisateur.class, cascade = CascadeType.PERSIST)
    private Utilisateur utilisateur;
    //@ManyToOne(targetEntity = Projet.class, cascade = CascadeType.PERSIST)
    //private Projet projet;
    @Column(nullable = false)
    private boolean actif;
    @Column(nullable = false)
    private boolean proprio;

}

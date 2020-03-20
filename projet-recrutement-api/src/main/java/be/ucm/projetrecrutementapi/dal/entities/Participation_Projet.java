package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    private Utilisateur utilisateur;
    //private Projet projet;
    private boolean actif;
    private boolean proprio;

}

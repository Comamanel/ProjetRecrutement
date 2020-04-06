package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.api.dto.TechnologieDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={ "id", "nom", "createur" })
public class Technologie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String createur;

    public Technologie(TechnologieDTO dto){
        this.id = dto.getId();
        this.nom = dto.getNom();
        this.createur = dto.getCreateur();
    }

}

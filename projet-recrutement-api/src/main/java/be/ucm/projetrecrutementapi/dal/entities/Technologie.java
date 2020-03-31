package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.api.dto.TechnologieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Technologie {

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

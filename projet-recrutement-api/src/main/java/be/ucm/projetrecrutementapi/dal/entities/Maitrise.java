package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={ "id", "niveauMaitrise", "technologie" })
@ToString(of={ "id", "niveauMaitrise", "technologie" })
public class Maitrise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private NiveauMaitrise niveauMaitrise;

    @ManyToOne
    private Technologie technologie;

}

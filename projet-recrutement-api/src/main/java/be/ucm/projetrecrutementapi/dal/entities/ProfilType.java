package be.ucm.projetrecrutementapi.dal.entities;

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
@AllArgsConstructor
public class ProfilType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;
    @Column(nullable = false, columnDefinition = "default true")
    boolean ouvert;

    @ManyToMany (cascade = CascadeType.DETACH)
    private Set<Maitrise> maitrisesDemandees = new HashSet<>();

    public ProfilType() {
        this.ouvert = true;
    }
}

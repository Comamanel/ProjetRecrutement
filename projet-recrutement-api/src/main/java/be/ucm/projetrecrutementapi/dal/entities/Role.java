package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={ "id", "label" })
public class Role {
    @Id
    private Long id;
    private String label;

}

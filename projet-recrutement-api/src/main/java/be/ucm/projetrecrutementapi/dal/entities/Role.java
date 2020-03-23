package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={ "id", "label" })
public class Role {
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String label;

}

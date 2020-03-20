package be.ucm.projetrecrutementapi.dal.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of={})
public class Group implements Serializable {
    @Id
    private Long id;
    private GroupEnum nom;

    @ManyToMany(targetEntity = Role.class)
    private Set<Role> roles;

    public Group(){
        this.roles = new HashSet<>();
    }

    public Group(Long id, GroupEnum nom) {
        this();
        this.id = id;
        this.nom = nom;
    }
}

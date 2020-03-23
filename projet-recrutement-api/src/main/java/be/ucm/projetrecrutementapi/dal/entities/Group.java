package be.ucm.projetrecrutementapi.dal.entities;

import be.ucm.projetrecrutementapi.dal.entities.enums.GroupEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of={ "id", "nom" })
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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

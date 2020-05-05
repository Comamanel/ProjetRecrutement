package be.ucm.projetrecrutementfront.models;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private Long id;
    private String nom;
    private Set<Role> roles;

    public Group() {
        this.roles = new HashSet<>();
    }

    public Group(Long id, String nom) {
        this();
        this.id = id;
        this.nom = nom;
    }

    public Group(Long id, String nom, Set<Role> roles) {
        this.id = id;
        this.nom = nom;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

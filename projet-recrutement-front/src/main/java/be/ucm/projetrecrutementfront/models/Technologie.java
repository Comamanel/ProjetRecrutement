package be.ucm.projetrecrutementfront.models;

public class Technologie {
    private Long id;
    private String nom;
    private String createur;

    public Technologie() {
    }

    public Technologie(Long id, String nom, String createur) {
        this();
        this.id = id;
        this.nom = nom;
        this.createur = createur;
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

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }
}

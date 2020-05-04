package be.ucm.projetrecrutementfront.models;

public class Maitrise {
    private Long id;
    private String niveauMaitrise;
    private Technologie technologie;

    public Maitrise() {
    }

    public Maitrise(Long id, String niveauMaitrise, Technologie technologie) {
        this();
        this.id = id;
        this.niveauMaitrise = niveauMaitrise;
        this.technologie = technologie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveauMaitrise() {
        return niveauMaitrise;
    }

    public void setNiveauMaitrise(String niveauMaitrise) {
        this.niveauMaitrise = niveauMaitrise;
    }

    public Technologie getTechnologie() {
        return technologie;
    }

    public void setTechnologie(Technologie technologie) {
        this.technologie = technologie;
    }
}

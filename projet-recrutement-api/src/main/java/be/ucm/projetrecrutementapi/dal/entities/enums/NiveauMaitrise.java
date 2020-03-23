package be.ucm.projetrecrutementapi.dal.entities.enums;

public enum NiveauMaitrise {
    DEB("Débutant"),
    INT("Intermédiaire"),
    AVA("Avancé");

    private String niveauMaitrise;

    public String getNiveauMaitrise() {
        return niveauMaitrise;
    }

    NiveauMaitrise(String niveauMaitrise) {
        this.niveauMaitrise = niveauMaitrise;
    }
}

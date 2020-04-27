package be.ucm.projetrecrutementapi.dal.entities.enums;

public enum NiveauMaitrise {
    DEB(1),
    INT(2),
    AVA(3);

    private int niveauMaitrise;

    public int getValeurNiveau() {
        return niveauMaitrise;
    }

    NiveauMaitrise(int niveauMaitrise) {
        this.niveauMaitrise = niveauMaitrise;
    }
}

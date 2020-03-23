package be.ucm.projetrecrutementapi.dal.entities.enums;

public enum EtatProjet {
    ACT("Actif"),
    ARC("Archivé");

    private String etatProjet;

    EtatProjet(String etatProjet) {
        this.etatProjet = etatProjet;
    }

    public String getEtatProjet() {
        return etatProjet;
    }
}

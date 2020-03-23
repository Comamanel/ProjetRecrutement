package be.ucm.projetrecrutementapi.dal.entities.enums;

public enum EtatCandidature {
    ARC("Archivé"),
    SUS("Suspendu"),
    ATT("En attente");

    private String etatCandidature;

    public String getEtatCandidature() {
        return etatCandidature;
    }

    EtatCandidature(String etatCandidature) {
        this.etatCandidature = etatCandidature;
    }
}

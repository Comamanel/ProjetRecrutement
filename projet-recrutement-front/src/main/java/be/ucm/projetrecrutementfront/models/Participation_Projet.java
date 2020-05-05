package be.ucm.projetrecrutementfront.models;

public class Participation_Projet {
    private Long id;
    private Projet projet;
    private Utilisateur utilisateur;
    private boolean actif;
    private boolean proprio;

    public Participation_Projet() {
    }

    public Participation_Projet(Long id, Projet projet, Utilisateur utilisateur, boolean actif, boolean proprio) {
        this.id = id;
        this.projet = projet;
        this.utilisateur = utilisateur;
        this.actif = actif;
        this.proprio = proprio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isProprio() {
        return proprio;
    }

    public void setProprio(boolean proprio) {
        this.proprio = proprio;
    }
}

package be.ucm.projetrecrutementapi.dal.entities.enums;

public enum TypeProjet {
    APP("Apprentissage"),
    SER("Sérieux");

    private String typeProjet;

    public String getTypeProjet() {
        return typeProjet;
    }

    TypeProjet(String typeProjet) {
        this.typeProjet = typeProjet;
    }
}

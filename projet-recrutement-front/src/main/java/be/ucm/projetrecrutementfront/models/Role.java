package be.ucm.projetrecrutementfront.models;

public class Role {
    private Long id;
    private String label;

    public Role() {
    }

    public Role(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

package be.ucm.projetrecrutementfront.models.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InscriptionUtilisateur {
    private String email;
    private String pseudo;
    private String motDePasse;
    private LocalDate dateDeNaissance;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
    }

    @Override
    public String toString() {
        return "InscriptionUtilisateur{" +
                "email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                '}';
    }
}

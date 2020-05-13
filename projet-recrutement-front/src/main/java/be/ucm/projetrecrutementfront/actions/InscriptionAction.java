package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import be.ucm.projetrecrutementfront.models.forms.InscriptionUtilisateur;
import be.ucm.projetrecrutementfront.services.UtilisateurService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.time.LocalDate;

public class InscriptionAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String confirmMotDePasse = "";
    private InscriptionUtilisateur utilisateur = new InscriptionUtilisateur();
    private Utilisateur utilisateurFinal;

    @Override
    public String execute() throws Exception {
        utilisateurFinal = UtilisateurService.getInstance().inscriptionUtilisateur(this.utilisateur);

        if(utilisateurFinal == null){
            addFieldError("utilisateur.email", "L'adresse e-mail est déjà utilisée");
            addFieldError("utilisateur.pseudo", "Le pseudo est déjà utilisé");
            return INPUT;
        }

        return SUCCESS;
    }

    @Action("inscription-input")
    public String input() throws Exception{
        return INPUT;
    }

    public void validate(){
        if (this.utilisateur.getPseudo().length() == 0) {
            addFieldError("utilisateur.pseudo", "Un pseudonyme est requis.");
        }
        else if(!(this.utilisateur.getPseudo().matches("^[^<>%$#=*/\\\\]*$"))){
            addFieldError("utilisateur.pseudo", "Pas de caractères spéciaux dans le pseudo");
        }

        if (this.utilisateur.getEmail().length() == 0) {
            addFieldError("utilisateur.email", "Un email est requis.");
        }
        else if(!(this.utilisateur.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))){
            addFieldError("utilisateur.email", "Entrez une adresse mail valide");
        }


        if(this.utilisateur.getMotDePasse().length() == 0){
            addFieldError("utilisateur.motDePasse", "Un mot de passe est requis.");
        }
        else if(!(this.utilisateur.getMotDePasse().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&éè]{8,}$"))){
            addFieldError("utilisateur.motDePasse", "Votre mot de passe doit faire minimum 8 caractères dont un en minuscule, un en majuscule, un chiffre et un caractère spécial");
        }
        else if(this.confirmMotDePasse.length() == 0){
            addFieldError("confirmMotDePasse", "Vous devez confirmer votre mot de passe.");
        }
        else if(!this.utilisateur.getMotDePasse().equals(this.confirmMotDePasse)){
            addFieldError("confirmMotDePasse", "Votre mot de passe doit être le même dans les deux champs");
        }

        if (this.utilisateur.getDateDeNaissance().isBefore(LocalDate.of(1900, 1, 1)) || this.utilisateur.getDateDeNaissance().isAfter(LocalDate.now())) {
            addFieldError("utilisateur.dateDeNaissance", "Entrez une date valide s'il vous plait");
        }
    }

    public InscriptionUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(InscriptionUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateurFinal() {
        return utilisateurFinal;
    }

    public void setUtilisateurFinal(Utilisateur utilisateurFinal) {
        this.utilisateurFinal = utilisateurFinal;
    }

    public String getConfirmMotDePasse() {
        return confirmMotDePasse;
    }

    public void setConfirmMotDePasse(String confirmMotDePasse) {
        this.confirmMotDePasse = confirmMotDePasse;
    }
}

package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.forms.InscriptionUtilisateur;
import be.ucm.projetrecrutementfront.services.UtilisateurService;
import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDate;

public class InscriptionAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private InscriptionUtilisateur utilisateur = new InscriptionUtilisateur();

    @Override
    public String execute() throws Exception {
        UtilisateurService.inscriptionUtilisateur(this.utilisateur);

        return SUCCESS;
    }

    public void validate(){
        if (this.utilisateur.getPseudo().length() == 0) {
            addFieldError("utilisateur.pseudo", "Un pseudonyme est requis.");
        }

        if (this.utilisateur.getEmail().length() == 0) {
            addFieldError("utilisateur.email", "Un email est requis.");
        }

        if(this.utilisateur.getMotDePasse().length() == 0){
            addFieldError("utilisateur.motDePasse", "Un mot de passe est requis.");
        }

        if (this.utilisateur.getDateDeNaissance().isBefore(LocalDate.of(1900, 1, 1)) || this.utilisateur.getDateDeNaissance().isAfter(LocalDate.now())) {
            addFieldError("utilisateur.dateDeNaissance", "Entrez une date valide s'il vous plait");
        }
        if(!(this.utilisateur.getPseudo().matches("^[^<>%$#=*/\\\\]*$"))){
            addFieldError("utilisateur.pseudo", "Entrez un pseudonyme valide s'il vous plat" );
        }
    }

    public InscriptionUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(InscriptionUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

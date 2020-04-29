package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

public class AfficheProfilUtilisateurAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Utilisateur utilisateur;

    @Action("")
    public String execute() throws Exception {


        return SUCCESS;
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

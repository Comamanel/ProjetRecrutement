package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

import java.util.List;

public class ListeUtilisateursAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<Utilisateur> utilisateurs;

    @Action("liste-utilisateurs")
    public String execute(){
        return SUCCESS;
    }

}

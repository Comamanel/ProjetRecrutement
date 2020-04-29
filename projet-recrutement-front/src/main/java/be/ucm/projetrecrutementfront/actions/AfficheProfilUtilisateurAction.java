package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

public class AfficheProfilUtilisateurAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Utilisateur utilisateur;
    private Long id;

    @Override
    public String execute() throws Exception {

        if(id != null) {
            //Appeler l'API pour peupler l'user
            utilisateur.setId(getId());
        }

        return SUCCESS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}

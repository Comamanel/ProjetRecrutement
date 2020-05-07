package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Projet;
import com.opensymphony.xwork2.ActionSupport;

public class ProjetPageAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Projet projet;

    @Override
    public String execute() throws Exception{
        return SUCCESS;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }


}

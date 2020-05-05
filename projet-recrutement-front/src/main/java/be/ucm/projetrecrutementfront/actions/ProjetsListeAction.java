package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Projet;
import be.ucm.projetrecrutementfront.services.ProjetService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class ProjetsListeAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Projet projet;
    private List<Projet> projets;

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

    public List<Projet> getProjets() {
        if(projets == null || projets.size() == 0){
            projet = new Projet();
            projets = ProjetService.getProjets();
        }
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

}

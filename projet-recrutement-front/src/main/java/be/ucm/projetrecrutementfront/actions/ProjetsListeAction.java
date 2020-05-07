package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Projet;
import be.ucm.projetrecrutementfront.services.ProjetService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Set;

public class ProjetsListeAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Projet projet;
    private Set<Projet> projets;

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

    public Set<Projet> getProjets() {
        if(projets == null || projets.size() == 0){
            System.out.println("Entré dans la condition");
            projet = new Projet();
            projets = ProjetService.getProjets();
            System.out.println(projets);
        }
        System.out.println("Liste récupérée :");
        System.out.println(projets);
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

}

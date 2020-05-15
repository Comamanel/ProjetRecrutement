package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Participation_Projet;
import be.ucm.projetrecrutementfront.models.Projet;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import be.ucm.projetrecrutementfront.services.ParticipationProjetService;
import be.ucm.projetrecrutementfront.services.ProjetService;
import be.ucm.projetrecrutementfront.services.UtilisateurService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Set;

public class ProjetPageAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Projet projet;
    private Set<Participation_Projet> participants;
    private Long id;

    @Override
    public String execute() throws Exception{
        if(id != null){
            this.projet = ProjetService.getInstance().getProjet(id);
            System.out.println(this.projet.getAdminId());
        }
        return SUCCESS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projet getProjet() {
        return this.projet;
    }
}

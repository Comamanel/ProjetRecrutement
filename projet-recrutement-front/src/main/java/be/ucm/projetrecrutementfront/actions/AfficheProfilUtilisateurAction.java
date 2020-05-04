package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Technologie;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import be.ucm.projetrecrutementfront.services.UtilisateurService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AfficheProfilUtilisateurAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Utilisateur utilisateur;
    private Long id;

    public String execute() throws Exception {

        if(id != null) {
            //Appeler l'API pour peupler l'user
            this.utilisateur = UtilisateurService.getUtilisateur(id);
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
        return this.utilisateur;
    }
}

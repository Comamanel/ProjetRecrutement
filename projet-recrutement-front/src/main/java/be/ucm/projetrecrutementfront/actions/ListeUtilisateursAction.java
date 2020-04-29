package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListeUtilisateursAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<Utilisateur> utilisateurs;

    @Override
    public String execute() throws Exception{
        return SUCCESS;
    }

    public List<Utilisateur> getUtilisateurs() {
        if(utilisateurs == null || utilisateurs.size() == 0){
            utilisateurs = new ArrayList<>();
            utilisateurs.add(new Utilisateur(
                    1L,
                    "test@test.com",
                    "coucouPseudo",
                    LocalDate.now(),
                    "testNom",
                    "TestPrenom",
                    "testInfosSupp",
                    "testNumTel",
                    "testPays",
                    "testLienGit",
                    "test photo",
                    "test cv"));
        }
        return utilisateurs;
    }
}

package be.ucm.projetrecrutementfront.actions;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Technologie;
import be.ucm.projetrecrutementfront.models.Utilisateur;
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
            Technologie technologie1 = new Technologie(1L, "Java" , "Sun Microsystem");
            Technologie technologie2 = new Technologie(2L, "Struts2" , "Apache");

            Set<Maitrise> maitrises = new HashSet<>();
            maitrises.add(new Maitrise(1L, "Débutant", technologie1));
            maitrises.add(new Maitrise(2L, "Débutant", technologie2));

            this.utilisateur = new Utilisateur(
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
                    "https://i.kym-cdn.com/entries/icons/original/000/000/091/TrollFace.jpg",
                    "test cv",
                    maitrises
                    );
            this.utilisateur.setId(getId());
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

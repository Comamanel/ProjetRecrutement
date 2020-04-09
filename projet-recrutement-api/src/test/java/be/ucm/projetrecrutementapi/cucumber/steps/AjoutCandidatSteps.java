package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import be.ucm.projetrecrutementapi.services.ProjetServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AjoutCandidatSteps {

    private ProjetService projetService;

    private ParticipationDAO participationDAO = Mockito.mock(ParticipationDAO.class);

    private Utilisateur utilisateurActif = new Utilisateur();
    private Projet projetDesire = new Projet();
    private Set<Participation_Projet> fausseDbParticipations = new HashSet<>();

    @Given("A user currently working on {int} projects")
    public void a_user_currently_working_on(int nbProjets){

        this.utilisateurActif.setPseudo("Test");
        this.utilisateurActif.setId((long) 2);

        for(int i = 0; i<nbProjets; i++) {
            Projet np = new Projet();
            np.setId((long) i);
            np.setNom("Projet " + i);
            np.setStatut(EtatProjet.ACT);

            Participation_Projet pp = new Participation_Projet();
            pp.setId((long) i);
            pp.setUtilisateur(utilisateurActif);
            pp.setProprio(false);
            pp.setActif(true);
            pp.setProjet(np);

            this.utilisateurActif.getProjetsParticipes().add(pp);
            System.out.println(utilisateurActif.getProjetsParticipes());
        }

    }

    @When("said user candidates to a project with currently {int} participants and a maximum of {int} participants")
    public void said_user_candidates_to_a_project(int nbUtils, int maxUtils){
        projetDesire.setId((long) 99);
        projetDesire.setNom("Nouveau Projet");
        projetDesire.setMaxParticipants(maxUtils);
        projetDesire.setStatut(EtatProjet.ACT);

        for(int i=0; i<nbUtils; i++){
            Utilisateur nu = new Utilisateur();
            nu.setPseudo("Utilisateur " + i);

            Participation_Projet pp = new Participation_Projet();
            pp.setId((long) 6 + i);
            pp.setProjet(projetDesire);
            pp.setActif(true);
            pp.setProprio(false);
            pp.setUtilisateur(nu);

            nu.getProjetsParticipes().add(pp);
            fausseDbParticipations.add(pp);

            this.projetService = new ProjetServiceImpl(participationDAO);
            when(participationDAO.findByProjectId(projetDesire.getId())).thenReturn(fausseDbParticipations);
        }

    }

    @Then("he can join the project")
    public void he_can_join_the_project(){
        Assert.assertNotNull(projetService.ajouterParticipant(projetDesire, utilisateurActif));
    }

    @Then("he cannot join the project")
    public void he_cannot_join_the_project(){
        Assert.assertNull(projetService.ajouterParticipant(projetDesire, utilisateurActif));
    }

}

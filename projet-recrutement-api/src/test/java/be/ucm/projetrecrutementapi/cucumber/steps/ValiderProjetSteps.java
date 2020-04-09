package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ValiderProjetSteps {

    private Utilisateur utilisateurActif;
    private Projet nouveauProjet;

    @Autowired
    private ProjetService projetService;

    @Given("a new project is initiated")
    public void a_new_project_is_initiated(){
        utilisateurActif = new Utilisateur();
        utilisateurActif.setId((long) 1);
        utilisateurActif.setNom("Cucumber");

        nouveauProjet = new Projet();
        nouveauProjet.setTypeProjet(TypeProjet.SER);
        nouveauProjet.setMaxParticipants(0);
        nouveauProjet.setTpsTravailHebdo(0);
    }

    @Given("the creator is currently working on {int} projects")
    public void the_creator_is_currently_working_on(int nbProjets){
        for(int i = 0; i<nbProjets; i++){
            Projet np = new Projet();
            np.setId((long) i);
            np.setNom("Projet" + i);

            Participation_Projet pp = new Participation_Projet();
            pp.setId((long) i);
            pp.setProjet(np);
            pp.setActif(true);
            pp.setProprio(false);
            utilisateurActif.getProjetsParticipes().add(pp);
        }
    }

    @Given("the creator is currently administrating {int} projects")
    public void the_creator_is_currently_administrating(int nbProjets){
        for(int i = 0; i<nbProjets; i++){
            Projet np = new Projet();
            np.setId((long) i);
            np.setNom("ProjetAdmin" + i);

            Participation_Projet pp = new Participation_Projet();
            pp.setId((long) i);
            pp.setProjet(np);
            pp.setActif(true);
            pp.setProprio(true);
            utilisateurActif.getProjetsCrees().add(np);
        }
    }

    @Given("the user is currently working on a project named {string}")
    public void the_user_is_currently_working_on_a_project_named(String nom){
        Projet np = new Projet();
        np.setNom(nom);
        np.setStatut(EtatProjet.ACT);

        Participation_Projet pp = new Participation_Projet();
        pp.setActif(true);
        pp.setProprio(true);
        pp.setProjet(np);

        utilisateurActif.getProjetsParticipes().add(pp);
    }

    @Given("the user has previously worked on a project named {string}")
    public void the_user_has_previously_worked_on_a_project_named(String nom){
        Projet np = new Projet();
        np.setNom(nom);
        np.setStatut(EtatProjet.ARC);

        Participation_Projet pp = new Participation_Projet();
        pp.setActif(true);
        pp.setProjet(np);

        utilisateurActif.getProjetsParticipes().add(pp);
    }

    @Given("said project is given the name {string}")
    public void said_project_is_given_the_name(String name){
        this.nouveauProjet.setNom(name);
    }

    @Given("said project begins on {string}")
    public void said_project_begins_on(String debutDate){
        this.nouveauProjet.setDateDebut(LocalDate.parse(debutDate));
    }

    @Given("said project is described as {string}")
    public void said_project_is_described_as(String description){
        this.nouveauProjet.setDescription(description);
    }

    @Given("said project ends on {string}")
    public void said_project_ends_on(String finDate){
        this.nouveauProjet.setDateFin(LocalDate.parse(finDate));
    }

    @Then("this project can be created")
    public void this_project_can_be_created(){
        Assert.assertNotNull(projetService.testerValiditeProjet(utilisateurActif, nouveauProjet));
    }

    @Then("this project cannot be created")
    public void this_project_cannot_be_created(){
        Assert.assertNull(projetService.testerValiditeProjet(utilisateurActif, nouveauProjet));
    }

}

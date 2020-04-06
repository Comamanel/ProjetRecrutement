package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class ProprieteProjetSteps {

    private Utilisateur utilisateur;
    private Utilisateur utilisateur2;
    private Utilisateur suppresseur;
    private Projet projet;

    @Autowired
    private ProjetService projetService;

    @Autowired
    private ParticipationDAO participationDAO;

    @Given("a user with the id {int}")
    public void a_user_with_the_id(int id){
        this.utilisateur = new Utilisateur();
        utilisateur.setId((long) id);
    }

    @Given("a project with the id {int}")
    public void a_project_with_the_id(int id){
        this.projet = new Projet();
        projet.setId((long) id);
    }

    @Given("said project is created by the user with the id {int}")
    public void said_project_is_created_by_the_user_with_the_id(int id){

        Participation_Projet pp = new Participation_Projet();
        pp.setProjet(this.projet);
        pp.setActif(true);

        if(id != this.utilisateur.getId()){
            this.utilisateur2 = new Utilisateur();
            this.utilisateur2.setId((long) id);
            this.utilisateur2.getProjetsCrees().add(this.projet);
            pp.setUtilisateur(this.utilisateur2);
            pp.setProprio(true);
        } else {
            this.utilisateur.getProjetsCrees().add(this.projet);
            pp.setUtilisateur(this.utilisateur);
            pp.setProprio(true);
        }

        participationDAO.save(pp);

    }

    @When("the user {int} wants to put an end to the project")
    public void the_user_wants_to_put_an_end_to_the_project(int id){
        if(id == this.utilisateur.getId()){
            this.suppresseur = this.utilisateur;
        } else {
            this.suppresseur = this.utilisateur2;
        }
    }

    @Then("the user can put the project to an end")
    public void the_user_can_put_the_project_to_an_en(){
        Assert.assertTrue(projetService.verifierProprieteProjet(this.suppresseur, this.projet));
    }

    @Then("the user cannot put the project to an end")
    public void the_user_cannot_put_the_project_to_an_en(){
        Assert.assertFalse(projetService.verifierProprieteProjet(this.suppresseur, this.projet));
    }

}

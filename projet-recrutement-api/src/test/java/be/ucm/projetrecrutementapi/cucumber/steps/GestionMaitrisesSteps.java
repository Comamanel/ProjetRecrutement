package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import be.ucm.projetrecrutementapi.services.ProjetService;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class GestionMaitrisesSteps {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProjetService projetService;

    private Utilisateur user = new Utilisateur();
    private Projet projet = new Projet();

    @Given("an user with the nickname {string}")
    public void a_user_with_the_nickname(String nickname){
        this.user.setPseudo(nickname);
    }

    @Given("a project with the name {string}")
    public void a_project_with_the_name(String name){ this.projet.setNom(name); }

    @Given("said user has informed he is {string} in the technology {string}")
    public void said_user_informed_he_is(String level, String technology){
        Maitrise nm =  new Maitrise();
        Technologie nt = new Technologie();

        nt.setNom(technology);
        nm.setTechnologie(nt);
        nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));

        this.user.getMaitrises().add(nm);
    }

    @Given("said project requires people being {string} in the technology {string}")
    public void said_project_requires(String level, String technology){
        Maitrise nm = new Maitrise();
        Technologie nt = new Technologie();

        nt.setNom(technology);
        nm.setTechnologie(nt);
        nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));

        this.projet.getMaitrisesDemandees().add(nm);
    }

    @When("he updates his list to inform that he is {string} in the technology {string}")
    public void he_updates_his_list(String level, String technology){
        Maitrise nm = new Maitrise();

        Technologie nt = new Technologie();
        nt.setNom(technology);

        nm.setTechnologie(nt);
        nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));

        this.user = utilisateurService.ajouterMaitrise(this.user, nm);
    }

    @When("he updates his list by removing the info about his skills in {string}")
    public void he_updates_hist_list_by_removing(String technology){

        Maitrise presenceMaitrise = this.user.getMaitrises().stream().filter(mu -> mu.getTechnologie().getNom().equals(technology)).findFirst().orElse(null);

        this.user = utilisateurService.retirerMaitrise(this.user, presenceMaitrise);

    }

    @When("the project is updated to demand people who are {string} in {string}")
    public void the_project_is_updated_to_demand(String level, String technology){
        Maitrise nm = new Maitrise();

        Technologie nt = new Technologie();
        nt.setNom(technology);

        nm.setTechnologie(nt);
        nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));

        this.projet = projetService.ajouterMaitrise(this. projet, nm);
    }

    @Then("his skills list contains {int} items")
    public void his_skills_list_contains(int nbItems){

        Assert.assertEquals(this.user.getMaitrises().size(), nbItems);

    }

    @Then("his level in the technology {string} is now {string}")
    public void his_level_in_the_technology_is_now(String technology, String level){

        Maitrise presenceMaitrise = this.user.getMaitrises().stream().filter(mu -> mu.getTechnologie().getNom().equals(technology)).findFirst().orElse(null);

        Assert.assertEquals(presenceMaitrise.getNiveauMaitrise().toString(), level);

    }

    @Then("the list of demanded skills contains {int} items")
    public void the_list_of_demanded_skills_contains(int nbItems){

        Assert.assertEquals(this.projet.getMaitrisesDemandees().size(), nbItems);

    }

}

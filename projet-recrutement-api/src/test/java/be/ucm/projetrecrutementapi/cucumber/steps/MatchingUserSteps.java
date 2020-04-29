package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import be.ucm.projetrecrutementapi.services.ProjetService;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatchingUserSteps {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProjetService projetService;

    private Utilisateur currentUser = new Utilisateur();
    private Projet currentProject = new Projet();
    private Set<Technologie> fauseDbTechnologies = new HashSet<>();

    private ProfilType profilTypeDummy = new ProfilType();
    private Set<ProfilType> profilTypes = new HashSet<>();

    @Given("an user with the username {string}")
    public void a_user_with_the_following_skills(String name){

        this.currentUser.setPseudo(name);

    }

    @Given("said user is {string} with the technology {string}")
    @Given("{string} with the technology {string}")
    public void said_user_is_with_the_technology(String level, String technology){

        Technologie nt = new Technologie();
        nt.setNom(technology);
        this.fauseDbTechnologies.add(nt);

        Maitrise nm = new Maitrise();
        nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));
        nm.setTechnologie(nt);

        this.currentUser.getMaitrises().add(nm);

    }

    @Given("a project administrator who wants people that are {string} in {string}")
    @Given("{string} in {string}")
    public void a_project_administrator_who_wants(String level, String technology){

        Technologie verifyNt = this.fauseDbTechnologies.stream().filter(mdb -> mdb.getNom().equals(technology)).findFirst().orElse(null);
        System.out.println(verifyNt);

        Maitrise nm = new Maitrise();

        if(verifyNt != null){
            nm.setTechnologie(verifyNt);
            nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));
        } else {
            Technologie nt = new Technologie();
            nt.setNom(technology);
            this.fauseDbTechnologies.add(nt);

            nm.setTechnologie(nt);
            nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));
        }

        this.profilTypeDummy.getMaitrisesDemandees().add(nm);

    }

    @When("said user consults a project that demands {string} users in {string}")
    @When("{string} users in {string}")
    public void said_user_consults_a_projects_that_demands(String level, String technology){

        Technologie verifyNt = this.fauseDbTechnologies.stream().filter(mdb -> mdb.getNom().equals(technology)).findFirst().orElse(null);
        System.out.println(verifyNt);

        Maitrise nm = new Maitrise();

        if(verifyNt != null){
            nm.setTechnologie(verifyNt);
            nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));
        } else {
            Technologie nt = new Technologie();
            nt.setNom(technology);
            this.fauseDbTechnologies.add(nt);

            nm.setTechnologie(nt);
            nm.setNiveauMaitrise(NiveauMaitrise.valueOf(level));
        }

        this.currentProject.getMaitrisesDemandees().add(nm);

    }

    @When("the user is consulting this project")
    public void the_user_is_consulting(){

        ProfilType insertPreviousPt = new ProfilType();
        insertPreviousPt.setId((long)this.profilTypes.size()+1);
        insertPreviousPt.setMaitrisesDemandees(this.profilTypeDummy.getMaitrisesDemandees());
        insertPreviousPt.setOuvert(this.profilTypeDummy.isOuvert());

        this.currentProject.getProfilsType().add(insertPreviousPt);

    }

    @Then("the skill matching score is above {int}%")
    @Then("above {int}%")
    public void the_matching_score_is_above(int matching){

        Assert.assertTrue(matching < (int) projetService.CalculerMatchingProjet(this.currentProject, this.currentUser));

    }

    @Then("the skill matching score is below {int}%")
    @Then("below {int}%")
    public void the_matching_score_is_below(int matching){

        Assert.assertTrue(matching > (int) projetService.CalculerMatchingProjet(this.currentProject, this.currentUser));

    }

    @Then("the profile matching score is above {int}%")
    public void the_pmatching_score_is_above(int matching){

        Assert.assertTrue(matching < (int) projetService.CalculerMatchingUtilisateur(this.currentProject, this.currentUser));

    }

    @Then("the profile matching score is below {int}%")
    public void the_pmatching_score_is_below(int matching){

        Assert.assertTrue(matching > (int) projetService.CalculerMatchingUtilisateur(this.currentProject, this.currentUser));

    }

}

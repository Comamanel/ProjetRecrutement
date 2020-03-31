package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class CreationUtilisateurSteps {

    private Utilisateur nouvelUtilisateur;

    @Autowired
    private UtilisateurService utilisateurService;

    @Given("someone wants to create an account")
    public void someone_wants_to_create_an_account(){
        this.nouvelUtilisateur = new Utilisateur();
    }

    @Given("the nickname he chose is {string}")
    public void the_nickname_he_chose_is(String pseudo){
        this.nouvelUtilisateur.setPseudo(pseudo);
    }

    @Given("the email he chose is {string}")
    public void the_email_he_chose_is(String email){
        this.nouvelUtilisateur.setEmail(email);
    }

    @Given("the password he chose is {string}")
    public void the_password_he_chose_is(String password){
        this.nouvelUtilisateur.setMotDePasse(password);
    }

    @Given("his birth date is {string}")
    public void his_birth_date_is(String birthDate) {
        this.nouvelUtilisateur.setDateDeNaissance(LocalDate.parse(birthDate));
    }

    @Then("this account can be created")
    public void this_account_can_be_created(){
        Assert.assertNotNull(utilisateurService.testerNouvelUtilisateur(this.nouvelUtilisateur));
    }

    @Then("this account cannot be created")
    public void this_account_cannot_be_created(){
        Assert.assertNull(utilisateurService.testerNouvelUtilisateur(this.nouvelUtilisateur));
    }
}

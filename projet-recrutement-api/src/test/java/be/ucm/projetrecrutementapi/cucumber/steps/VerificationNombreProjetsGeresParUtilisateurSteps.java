package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.cucumber.TestsData;
import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.services.UtilisateurServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;

public class VerificationNombreProjetsGeresParUtilisateurSteps {

    private boolean isUtilisateurProprietaireDeMoinsDeTroisProjets;

    @Given("un utilisateur avec comme email {string}, {string} comme mdp et un pseudo {string}")
    public void unUtilisateurAvecCommeEmailCommeMdpEtUnPseudo(String email, String mdp, String pseudo) {
        TestsData.utilisateur = new Utilisateur();
        TestsData.utilisateur.setEmail(email);
        TestsData.utilisateur.setMotDePasse(mdp);
        TestsData.utilisateur.setPseudo(pseudo);
    }

    @Given("un projet avec comme id {long}, comme nom {string}, la description {string}, une date de début fixée au {string}, un type de projet fixé à {string} avec {int} participants maximum et un statut fixé à {string}")
    public void unProjetAvecCommeNomLaDescriptionUneDateDeDebutFixeeAuUnTypeDeProjetFixeAAvecParticipantsMaximumEtUnStatutFixeA(long id, String nom, String description, String dateDebut, String typeProjet, int nbParticipants, String statut) {
        TestsData.projet = new Projet();
        TestsData.projet.setId(id);
        TestsData.projet.setNom(nom);
        TestsData.projet.setDescription(description);
        TestsData.projet.setDateDebut(LocalDate.parse(dateDebut));
        TestsData.projet.setTypeProjet(TypeProjet.valueOf(typeProjet));
        TestsData.projet.setMaxParticipants(nbParticipants);
        TestsData.projet.setStatut(EtatProjet.valueOf(statut));
    }

    @Given("une participation projet active à {bool} et proprio à {bool}")
    public void uneParticipationProjetActiveAEtProprioA(boolean actif, boolean proprio) {
        Participation_Projet participationProjet = new Participation_Projet();
        participationProjet.setActif(actif);
        participationProjet.setProprio(proprio);
        //participationProjet.setUtilisateur(TestsData.utilisateur);
        participationProjet.setProjet(TestsData.projet);

        TestsData.utilisateur.getProjetsParticipes().add(participationProjet);
    }

    @When("on teste si on peut le mettre propriétaire d'un autre projet")
    public void onTesteSiOnPeutLeMettreProprietaireDUnAutreProjet() {
        UtilisateurServiceImpl utilisateurService = new UtilisateurServiceImpl();
        this.isUtilisateurProprietaireDeMoinsDeTroisProjets = utilisateurService.checkUtilisateurNEstPasProprietaireDePlusDeDeuxProjets(TestsData.utilisateur);
    }

    @Then("la réponse est {bool}")
    public void laReponseEst(boolean reponseAttendue) {
        Assert.assertEquals(reponseAttendue, this.isUtilisateurProprietaireDeMoinsDeTroisProjets);
    }
}

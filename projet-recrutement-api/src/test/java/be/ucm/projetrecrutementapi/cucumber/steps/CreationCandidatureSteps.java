package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.cucumber.TestsData;
import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.services.CandidatureServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreationCandidatureSteps {
    List<Technologie> technologies = new ArrayList<>();
    List<Maitrise> maitrises = new ArrayList<>();
    Candidature candidature;
    Boolean isCandidOk;

    @Given("la technologie {string}, créée par {string}")
    public void laTechnologieCreeePar(String nomTechno, String createurTechno) {
        Technologie techno = new Technologie();
        techno.setNom(nomTechno);
        techno.setCreateur(createurTechno);
        this.technologies.add(techno);
    }

    @Given("la maîtrise contenant la techno {string}, de niveau {string}")
    public void laMaitriseContenantLaTechnoDeNiveau(String nomTechno, String niveauMaitrise) throws Exception {
        Maitrise maitrise = new Maitrise();
        maitrise.setTechnologie(
                this.technologies
                        .stream()
                        .filter(t -> t.getNom().equalsIgnoreCase(nomTechno))
                        .findFirst()
                        .orElse(null)
        );
        if(maitrise.getTechnologie() == null)
            throw new Exception("souci au niveau de la récupération de la techno");
        maitrise.setNiveauMaitrise(NiveauMaitrise.valueOf(niveauMaitrise));
        this.maitrises.add(maitrise);
    }

    @Given("un utilisateur avec un email {string}, {string} comme mdp, un pseudo {string} la maîtrise en {string} pour la technologie {string} et la maîtrise en {string} pour la technologie {string}")
    public void unUtilisateurAvecUnEmailCommeMdpUnPseudoLaMaitriseEnPourLaTechnologieEtLaMaitriseEnPourLaTechnologie(String email, String mdp, String pseudo, String niveauMaitriseUn, String nomMaitriseUn, String niveauMaitriseDeux, String nomMaitriseDeux) throws Exception {
        TestsData.utilisateur = new Utilisateur();
        TestsData.utilisateur.setEmail(email);
        TestsData.utilisateur.setMotDePasse(mdp);
        TestsData.utilisateur.setPseudo(pseudo);

        Maitrise maitrise1 = this.maitrises
                .stream()
                .filter(m -> m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseUn) && m.getNiveauMaitrise().equals(NiveauMaitrise.valueOf(niveauMaitriseUn)))
                .findFirst()
                .orElse(null);

        Maitrise maitrise2 = this.maitrises
                .stream()
                .filter(m -> m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseDeux) && m.getNiveauMaitrise().equals(NiveauMaitrise.valueOf(niveauMaitriseDeux)))
                .findFirst()
                .orElse(null);

        if(maitrise1 == null || maitrise2 == null)
            throw new Exception("souci au niveau de la récupération de la maîtrise");

        TestsData.utilisateur.getMaitrises().add(maitrise1);
        TestsData.utilisateur.getMaitrises().add(maitrise2);
    }

    @Given("un utilisateur avec un email {string}, {string} comme mdp, un pseudo {string} et la maîtrise en {string} pour la technologie {string}")
    public void unUtilisateurAvecUnEmailCommeMdpUnPseudoEtLaMaîtriseEnPourLaTechnologie(String email, String mdp, String pseudo, String niveauMaitriseUn, String nomMaitriseUn) throws Exception {
        TestsData.utilisateur = new Utilisateur();
        TestsData.utilisateur.setEmail(email);
        TestsData.utilisateur.setMotDePasse(mdp);
        TestsData.utilisateur.setPseudo(pseudo);

        Maitrise maitrise1 = this.maitrises
                .stream()
                .filter(m -> m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseUn) && m.getNiveauMaitrise().equals(NiveauMaitrise.valueOf(niveauMaitriseUn)))
                .findFirst()
                .orElse(null);

        if(maitrise1 == null)
            throw new Exception("souci au niveau de la récupération de la maîtrise");

        TestsData.utilisateur.getMaitrises().add(maitrise1);
    }

    @Given("un utilisateur avec un email {string}, {string} comme mdp et un pseudo {string}")
    public void unUtilisateurAvecUnEmailCommeMdpEtUnPseudo(String email, String mdp, String pseudo) {
        TestsData.utilisateur = new Utilisateur();
        TestsData.utilisateur.setEmail(email);
        TestsData.utilisateur.setMotDePasse(mdp);
        TestsData.utilisateur.setPseudo(pseudo);
    }

    @Given("un projet avec un nom {string}, la description {string}, une date de début fixée au {string}, un type de projet fixé à {string} avec {int} participants maximum et un statut fixé à {string}, une maîtrise en {string} demandée au niveau {string}, et une en {string} demandée au niveau {string}")
    public void unProjetAvecUnNomLaDescriptionUneDateDeDebutFixeeAuUnTypeDeProjetFixeAAvecParticipantsMaximumEtUnStatutFixeAUneMaitriseEnDemandeeAuNiveauEtUneEnDemandeeAuNiveau(String nom, String description, String dateDebut, String typeProjet, int nbParticipants, String statut, String nomMaitriseUn, String niveauMaitriseUn, String nomMaitriseDeux, String niveauMaitriseDeux) {
        TestsData.projet = new Projet();
        TestsData.projet.setNom(nom);
        TestsData.projet.setDescription(description);
        TestsData.projet.setDateDebut(LocalDate.parse(dateDebut));
        TestsData.projet.setTypeProjet(TypeProjet.valueOf(typeProjet));
        TestsData.projet.setMaxParticipants(nbParticipants);
        TestsData.projet.setStatut(EtatProjet.valueOf(statut));

        this.maitrises
                .stream()
                .filter(m ->
                        (m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseUn) && m.getNiveauMaitrise().equals(NiveauMaitrise.valueOf(niveauMaitriseUn)))
                        ||
                        (m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseDeux) && m.getNiveauMaitrise().equals(NiveauMaitrise.valueOf(niveauMaitriseDeux)))
                )
                .forEach(m -> TestsData.projet.getMaitrisesDemandees().add(m));
    }

    @Given("une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celles avec le nom {string} et le nom {string}, le statut à {string}, le nombre d'heures par semaines fixées à {int}")
    public void uneCandidatureContenantLeProjetLUtilisateurAvecToutesLesMaitrisesDeLUtilisateurCocheesLeStatutALeNombreDHeuresParSemainesFixeesA(String nomTechno1, String nomTechno2, String statut, int heuresSemaine) {
        this.candidature = new Candidature();
        this.candidature.setUtilisateur(TestsData.utilisateur);
        this.candidature.setProjet(TestsData.projet);
        this.candidature.setStatut(EtatCandidature.valueOf(statut));
        this.candidature.setNbHeuresSemaine(heuresSemaine);

        this.technologies
                .stream()
                .filter(t -> t.getNom().equalsIgnoreCase(nomTechno1) || t.getNom().equalsIgnoreCase((nomTechno2)))
                .forEach(t -> this.candidature.getTechnologieSouhaitee().add(t));

    }

    @Given("une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celle avec le nom {string}, le statut à {string}, le nombre d'heures par semaines fixées à {int}")
    public void uneCandidatureContenantLeProjetLUtilisateurAvecEnGuiseDeTechnologiesCocheesCelleAvecLeNomLeStatutALeNombreDHeuresParSemainesFixeesA(String nomTechno, String statut, int heuresSemaine) {
        this.candidature = new Candidature();
        this.candidature.setUtilisateur(TestsData.utilisateur);
        this.candidature.setProjet(TestsData.projet);
        this.candidature.setStatut(EtatCandidature.valueOf(statut));
        this.candidature.setNbHeuresSemaine(heuresSemaine);

        this.technologies
                .stream()
                .filter(t -> t.getNom().equalsIgnoreCase(nomTechno))
                .forEach(t -> this.candidature.getTechnologieSouhaitee().add(t));
    }

    @Given("une candidature contenant le projet, l'utilisateur, avec aucune technologie cochée, le statut à {string}, le nombre d'heures par semaines fixées à {int}")
    public void uneCandidatureContenantLeProjetLUtilisateurAvecAucuneTechnologieCocheeLeStatutALeNombreDHeuresParSemainesFixeesA(String statut, int heuresSemaine) {
        this.candidature = new Candidature();
        this.candidature.setUtilisateur(TestsData.utilisateur);
        this.candidature.setProjet(TestsData.projet);
        this.candidature.setStatut(EtatCandidature.valueOf(statut));
        this.candidature.setNbHeuresSemaine(heuresSemaine);
    }

    @When("je vérifie si la candidature est bonne")
    public void jeVerifieSiLaCandidatureEstBonne() {
        CandidatureServiceImpl candidatureService = new CandidatureServiceImpl();
        this.isCandidOk = candidatureService.testsBooleanOk(this.candidature);
    }

    @Then("la vérification de la véracité de la candidature est à {bool}")
    public void laVerificationDeLaVeraciteDeLaCandidatureEstA(Boolean resultatAttendu) {
        Assert.assertEquals(resultatAttendu, isCandidOk);
    }
}

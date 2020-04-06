package be.ucm.projetrecrutementapi.cucumber.steps;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreationCandidatureSteps {
    List<Technologie> technologies = new ArrayList<>();
    List<Maitrise> maitrises = new ArrayList<>();
    Utilisateur utilisateur;
    Projet projet;
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
        this.utilisateur = new Utilisateur();
        this.utilisateur.setEmail(email);
        this.utilisateur.setMotDePasse(mdp);
        this.utilisateur.setPseudo(pseudo);

        Maitrise maitrise1 = this.maitrises
                .stream()
                .filter(m -> m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseUn) && m.getNiveauMaitrise().getNiveauMaitrise().equalsIgnoreCase(niveauMaitriseUn))
                .findFirst()
                .orElse(null);

        Maitrise maitrise2 = this.maitrises
                .stream()
                .filter(m -> m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseDeux) && m.getNiveauMaitrise().getNiveauMaitrise().equalsIgnoreCase(niveauMaitriseDeux))
                .findFirst()
                .orElse(null);

        if(maitrise1 == null || maitrise2 == null)
            throw new Exception("souci au niveau de la récupération de la maîtrise");

        this.utilisateur.getMaitrises().add(maitrise1);
        this.utilisateur.getMaitrises().add(maitrise2);
    }

    @Given("un projet avec un nom {string}, la description {string}, une date de début fixée au {string}, un type de projet fixé à {string} avec {int} participants maximum et un statut fixé à {string}, une maîtrise en {string} demandée en {string}, et une en {string} demandée en {string}")
    public void unProjetAvecUnNomLaDescriptionUneDateDeDebutFixeeAuUnTypeDeProjetFixeAAvecParticipantsMaximumEtUnStatutFixeAUneMaitriseEnDemandeeEnEtUneEnDemandeeEn(String nom, String description, String dateDebut, String typeProjet, int nbParticipants, String statut, String niveauMaitriseUn, String nomMaitriseUn, String niveauMaitriseDeux, String nomMaitriseDeux) {
        this.projet = new Projet();
        this.projet.setNom(nom);
        this.projet.setDescription(description);
        this.projet.setDateDebut(LocalDate.parse(dateDebut));
        this.projet.setTypeProjet(TypeProjet.valueOf(typeProjet));
        this.projet.setMaxParticipants(nbParticipants);
        this.projet.setStatut(EtatProjet.valueOf(statut));

        this.maitrises
                .stream()
                .filter(m ->
                        m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseUn) && m.getNiveauMaitrise().getNiveauMaitrise().equalsIgnoreCase(niveauMaitriseUn)
                        ||
                        m.getTechnologie().getNom().equalsIgnoreCase(nomMaitriseDeux) && m.getNiveauMaitrise().getNiveauMaitrise().equalsIgnoreCase(niveauMaitriseDeux)
                )
                .forEach(m -> this.projet.getMaitrisesDemandees().add(m));
    }


    @Given("une candidature contenant le projet, l'utilisateur, avec toutes les maîtrises de l'utilisateur cochées, le statut à {string}, le nombre d'heures par semaines fixées à {int}")
    public void uneCandidatureContenantLeProjetLUtilisateurAvecToutesLesMaitrisesDeLUtilisateurCocheesLeStatutALeNombreDHeuresParSemainesFixeesA(String statut, int heuresSemaine) {
        this.candidature = new Candidature();
        this.candidature.setUtilisateur(this.utilisateur);
        this.candidature.setProjet(this.projet);
        this.candidature.setStatut(EtatCandidature.valueOf(statut));
        this.candidature.setNbHeuresSemaine(heuresSemaine);

    }

    @When("je vérifie si la candidature est bonne")
    public void jeVerifieSiLaCandidatureEstBonne() {
        CandidatureServiceImpl candidatureService = new CandidatureServiceImpl();
        this.isCandidOk = candidatureService.testsBooleanOk(this.candidature);
    }


    //TODO : Faire un custom pour avoir direct un booléen de ses morts
    @Then("la vérification de la véracité de la candidature est à true")
    public void laVérificationDeLaVéracitéDeLaCandidatureEstÀTrue(Boolean resultatAttendu) {
    }
}

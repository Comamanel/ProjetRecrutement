package be.ucm.projetrecrutementapi.rest;

import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProjetFiltreRestTest {
    private int bound;
    private String dateLimite;

    @Test
    public void ProjetDAOFiltre_NomContientSushi_LesProjetsAvecSushiDansLeNom(){
        given()
                .param("nom", "Sushi")
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", everyItem(containsString("Sushi")));
    }

    @Test
    public void ProjetDAOFiltre_NomContientProjet_LesProjetsAvecProjetDansLeNom(){
        given()
                .param("nom", "Projet")
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", everyItem(containsString("Projet")));
    }

    @Test
    public void ProjetDAOFiltre_participantsMaxEgal10_LesProjetsAvec10ParticipantsMax(){
        this.bound = 10;
        given()
                .param("maxParticipantsEgalA", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(equalTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_participantsMaxInferieur2_LesProjetsAvecMoinsDeDeuxParticipantsMax(){
        this.bound = 2;
        given()
                .param("maxParticipantsInferieurA", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(lessThanOrEqualTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_participantsMaxSuperieurA5_LesProjetsAvecPlusDe5ParticipantsMax(){
        this.bound = 5;
        given()
                .param("maxParticipantsSuperieurA", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(greaterThanOrEqualTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_dateDebutEgal23Mars2020_LesProjetsAyantCommenceLe23Mars2020(){
        this.dateLimite = "2020-03-23";
        given()
                .param("dateDebutEgal", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateDebut", everyItem(equalTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_dateDebutAvant2020_LesProjetsAyantCommencéAuPlusTardLe31Decembre2019(){
        this.dateLimite = "2019-12-31";
        given()
                .param("dateFinAvant", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(lessThanOrEqualTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_dateDebutApresLePremierFevrier2020_LesProjetsAyantCommenceApresLePremierFevrier2020(){
        this.dateLimite = "2020-02-01";
        given()
                .param("dateDebutApres", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateDebut", everyItem(greaterThanOrEqualTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_dateFinEgalPremierDecembre2020_LesProjetsSeFinissantLePremierDecembre2020(){
        this.dateLimite = "2020-12-01";
        given()
                .param("dateFinEgal", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(equalTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_dateFinAvantPremierDecembre2040_LesProjetsSeFinissantAvantLePremierDecembre2040(){
        this.dateLimite = "2040-12-01";
        given()
                .param("dateFinAvant", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(lessThanOrEqualTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_dateFinApresPremierDecembre2060_LesProjetsSeFinissantApresLePremierDecembre2060(){
        this.dateLimite = "2060-12-01";
        given()
                .param("dateFinApres", this.dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(greaterThanOrEqualTo(this.dateLimite)));
    }

    @Test
    public void ProjetDAOFiltre_tpsTravailHebdoEgal4_LesProjetsAyantExactement4HeuresHebdoDemandees(){
        this.bound = 4;
        given()
                .param("tpsTravailHebdoEgal", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(equalTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_tpsTravailHebdoInferieurA6_LesProjetsAyantMoinsDe6HeuresHebdoDemandees(){
        this.bound = 6;
        given()
                .param("tpsTravailHebdoInferieurA", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(lessThanOrEqualTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_tpsTravailHebdoSuperieurA5_LesProjetsAyantPlusDe5HeuresHebdoDemandees(){
        this.bound = 5;
        given()
                .param("tpsTravailHebdoSuperieurA", this.bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(greaterThanOrEqualTo(this.bound)));
    }

    @Test
    public void ProjetDAOFiltre_etatProjetEstActif_LesProjetsAyantUnEtatFixeAActif(){
        EtatProjet etat = EtatProjet.ACT;
        given()
                .param("statut", etat)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("statut", everyItem(equalTo("ACT")));
    }

    @Test
    public void ProjetDAOFiltre_typeProjetEstSerieux_LesProjetsDontLeTypeEstSerieux(){
        given()
                .param("typeProjet", TypeProjet.SER)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("typeProjet", everyItem(equalTo("SER")));
    }

    @Test
    public void ProjetDAOFiltre_typeProjetEstApprentissage_LesProjetsDontLeTypeEstApprentissage(){
        given()
                .param("typeProjet", TypeProjet.APP)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("typeProjet", everyItem(equalTo("APP")));
    }
}

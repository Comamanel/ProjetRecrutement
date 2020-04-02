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

    @Test
    public void projetDAOFiltre_NomContientSushi_LesProjetsAvecSushiDansLeNom(){
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
    public void projetDAOFiltre_NomContientProjet_LesProjetsAvecProjetDansLeNom(){
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
    public void projetDAOFiltre_participantsMaxEgal10_LesProjetsAvec10ParticipantsMax(){
        int bound = 10;
        given()
                .param("maxParticipantsEgalA", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(equalTo(bound)));
    }

    @Test
    public void projetDAOFiltre_participantsMaxInferieur2_LesProjetsAvecMoinsDeDeuxParticipantsMax(){
        int bound = 2;
        given()
                .param("maxParticipantsInferieurA", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(lessThanOrEqualTo(bound)));
    }

    @Test
    public void projetDAOFiltre_participantsMaxSuperieurA5_LesProjetsAvecPlusDe5ParticipantsMax(){
        int bound = 5;
        given()
                .param("maxParticipantsSuperieurA", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("maxParticipants", everyItem(greaterThanOrEqualTo(bound)));
    }

    @Test
    public void projetDAOFiltre_dateDebutEgal23Mars2020_LesProjetsAyantCommenceLe23Mars2020(){
        String dateLimite = "2020-03-23";
        given()
                .param("dateDebutEgal", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateDebut", everyItem(equalTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_dateDebutAvant2020_LesProjetsAyantCommenceAuPlusTardLe31Decembre2019(){
        String dateLimite = "2019-12-31";
        given()
                .param("dateFinAvant", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(lessThanOrEqualTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_dateDebutApresLePremierFevrier2020_LesProjetsAyantCommenceApresLePremierFevrier2020(){
        String dateLimite = "2020-02-01";
        given()
                .param("dateDebutApres", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateDebut", everyItem(greaterThanOrEqualTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_dateFinEgalPremierDecembre2020_LesProjetsSeFinissantLePremierDecembre2020(){
        String dateLimite = "2020-12-01";
        given()
                .param("dateFinEgal", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(equalTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_dateFinAvantPremierDecembre2040_LesProjetsSeFinissantAvantLePremierDecembre2040(){
        String dateLimite = "2040-12-01";
        given()
                .param("dateFinAvant", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(lessThanOrEqualTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_dateFinApresPremierDecembre2060_LesProjetsSeFinissantApresLePremierDecembre2060(){
        String dateLimite = "2060-12-01";
        given()
                .param("dateFinApres", dateLimite)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("dateFin", everyItem(greaterThanOrEqualTo(dateLimite)));
    }

    @Test
    public void projetDAOFiltre_tpsTravailHebdoEgal4_LesProjetsAyantExactement4HeuresHebdoDemandees(){
        int bound = 4;
        given()
                .param("tpsTravailHebdoEgal", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(equalTo(bound)));
    }

    @Test
    public void projetDAOFiltre_tpsTravailHebdoInferieurA6_LesProjetsAyantMoinsDe6HeuresHebdoDemandees(){
        int bound = 6;
        given()
                .param("tpsTravailHebdoInferieurA", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(lessThanOrEqualTo(bound)));
    }

    @Test
    public void projetDAOFiltre_tpsTravailHebdoSuperieurA5_LesProjetsAyantPlusDe5HeuresHebdoDemandees(){
        int bound = 5;
        given()
                .param("tpsTravailHebdoSuperieurA", bound)
                .when()
                .get("http://localhost:8080/api/projet/")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tpsTravailHebdo", everyItem(greaterThanOrEqualTo(bound)));
    }

    @Test
    public void projetDAOFiltre_etatProjetEstActif_LesProjetsAyantUnEtatFixeAActif(){
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
    public void projetDAOFiltre_typeProjetEstSerieux_LesProjetsDontLeTypeEstSerieux(){
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
    public void projetDAOFiltre_typeProjetEstApprentissage_LesProjetsDontLeTypeEstApprentissage(){
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

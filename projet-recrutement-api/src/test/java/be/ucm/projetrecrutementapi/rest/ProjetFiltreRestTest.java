package be.ucm.projetrecrutementapi.rest;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProjetFiltreRestTest {
    private int bound;
    private LocalDate dateLimite;

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
    public void ProjetDAOFiltre_dateFinEgalPremierDecembre2020_LesProjetsSeFinissantLePremierDecembre2020(){
        this.dateLimite = LocalDate.of(2020, 12, 1);
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
}

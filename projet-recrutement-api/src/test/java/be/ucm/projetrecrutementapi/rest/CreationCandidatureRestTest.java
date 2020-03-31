package be.ucm.projetrecrutementapi.rest;

import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.api.dto.TechnologieDTO;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreationCandidatureRestTest {

    @Test
    public void candidatureControllerCreateCandidature_CasNormalFonctionnel_UnCode200(){
        CandidatureFormulaireDTO candidatureFormulaireDTO = new CandidatureFormulaireDTO();
        candidatureFormulaireDTO.setNbHeuresSemaine(10);
        candidatureFormulaireDTO.setProjetId(1L);
        candidatureFormulaireDTO.setUtilisateurId(1L);
        Set<Long> technologies = new HashSet<>();
        technologies.add(1L);
        technologies.add(2L);
        candidatureFormulaireDTO.setTechnologiesSouhaitees(technologies);

        given()
                .contentType(ContentType.JSON)
                .body(candidatureFormulaireDTO)
                .when()
                .post("http://localhost:8080/api/candidature/new")
                .then()
                .assertThat()
                .statusCode(200)
                .body("utilisateur.id", equalTo(1))
                .and()
                .body("projet.id", equalTo(1));
    }
}

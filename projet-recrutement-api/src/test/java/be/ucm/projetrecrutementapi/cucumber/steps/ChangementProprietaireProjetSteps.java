package be.ucm.projetrecrutementapi.cucumber.steps;

import be.ucm.projetrecrutementapi.api.dto.ChangementProprietaireFormulaire;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ChangementProprietaireProjetSteps {

    private RequestSpecification requestSpecification;
    private Response response;
    private ValidatableResponse validatableResponse;
    private ChangementProprietaireFormulaire changementProprietaireFormulaire;

    @Given("un formulaire de changement de propriétaire envoyant comme id de l'ancien propriétaire {long}, comme id du nouveau propriétaire {long}, comme id du projet {long}")
    public void unFormulaireDeChangementDeProprietaireEnvoyantCommeIdDeLAncienProprietaireCommeIdDuNouveauProprietaireCommeIdDuProjet(Long ancienProprietaireId, Long nouveauProprietaireId, Long projetId) {
        this.changementProprietaireFormulaire = new ChangementProprietaireFormulaire();
        this.changementProprietaireFormulaire.setAncienProprietaire(ancienProprietaireId);
        this.changementProprietaireFormulaire.setNouveauProprietaire(nouveauProprietaireId);
        this.changementProprietaireFormulaire.setProjet(projetId);
    }

    @Given("une requête en {string} sans body")
    public void uneRequeteEnSansBody(String contentType){
        this.requestSpecification = given().contentType(ContentType.valueOf(contentType));
    }

    @Given("une requête en {string} avec body contenant un string vide")
    public void uneRequeteEnAvecBodyContenantUnStringVide(String contentType) {
        this.requestSpecification = given().contentType(ContentType.valueOf(contentType)).body("");
    }

    @Given("une requête en {string} avec body contenant le formulaire de changement de propriétaire")
    public void uneRequeteEnAvecBodyContenantLeFormulaireDeChangementDeProprietaire(String contentType) {
        this.requestSpecification = given().contentType(ContentType.valueOf(contentType)).body(this.changementProprietaireFormulaire);
    }

    @When("je contacte l'adresse {string}")
    public void jeContacteLAdresse(String lien) {
        this.response = this.requestSpecification.when()
                .post(lien);

    }

    @Then("je reçois une réponse avec un code {int}")
    public void jeRecoisUnReponseAvecUnCode(int numCode) {
        this.validatableResponse = this.response.then()
                .assertThat()
                .statusCode(numCode);
    }

    @Then("un body avec {bool}")
    public void unBodyAvecFalse(boolean body) {
        this.validatableResponse.body("response", equalTo(body));
    }
}

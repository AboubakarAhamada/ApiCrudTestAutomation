package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonSteps {

    private String endpoint;
    private Map<String, Object> payload = new HashMap<>();
    private Response response;

    @Before
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // ------------- GIVEN -------------

    @Given("un endpoint {string}")
    public void unEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Given("un payload JSON")
    public void unPayloadJSON(DataTable table) {
        payload.clear();
        Map<String, String> raw = table.asMap(String.class, String.class);
        raw.forEach((k, v) -> payload.put(k, parse(v)));
    }

    // ------------- WHEN -------------

    @When("j'envoie une requête GET pour l'id {int}")
    public void sendGet(Integer id) {
        response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get(endpoint + "/" + id);
        System.out.println(response.asString());
    }


    @When("j'envoie une requête POST vers {string}")
    public void post_vers(String ep) {
        this.response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(this.payload)
                        .when()
                        .post(ep);
    }

    @When("j'envoie une requête PUT pour l'id {int}")
    public void sendPut(Integer id) {
        response =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .put(endpoint + "/" + id);
    }

    @When("j'envoie une requête PATCH pour l'id {int}")
    public void sendPatch(Integer id) {
        response =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .patch(endpoint + "/" + id);
    }

    @When("j'envoie une requête DELETE pour l'id {int}")
    public void sendDelete(Integer id) {
        response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .delete(endpoint + "/" + id);
    }

    // ------------- THEN -------------

    @Then("le status code doit être {int}")
    public void status(Integer code) {
        response.then().statusCode(code);
    }

    @Then("le Content-Type doit être JSON")
    public void checkJson() {
        response.then().contentType(containsString("application/json"));
    }


    @Then("le status code doit être l'un de")
    public void status_code_in(DataTable table) {
        // La DataTable est une colonne de codes (ex: | 201 | \n | 200 |)
        List<Integer> accepted = table.asList().stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int actual = this.response.getStatusCode();
        assertThat(accepted)
                .as("Le status %s devrait être dans %s", actual, accepted)
                .contains(actual);
    }


    @Then("la réponse contient les clés")
    public void keys(DataTable table) {
        List<String> expected = table.asList();
        Map<String, Object> json = response.jsonPath().getMap("$");
        assertThat(json.keySet()).containsAll(expected);
    }


    @Then("la réponse reflète le payload envoyé")
    public void reflectPayload() {
        Map<String, Object> json = response.jsonPath().getMap("$");
        payload.forEach((k, v) ->
                assertThat(json.get(k)).isEqualTo(v));
    }

    @And("le champ \"id\" est non nul")
    public void idIsNotEmpty(){

    }

    @Then("la réponse est vide")
    public void emptyBody() {
        String b = response.asString().trim();
        assertThat(b).isIn("", "{}", "null");
    }

    // ------------- Utils -------------

    private Object parse(String raw) {
        if (raw.matches("^-?\\d+$")) return Integer.parseInt(raw);
        if (raw.equalsIgnoreCase("true") || raw.equalsIgnoreCase("false"))
            return Boolean.parseBoolean(raw);
        return raw;
    }
}


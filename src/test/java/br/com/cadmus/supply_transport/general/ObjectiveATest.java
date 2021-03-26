package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.IntegrationTestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ObjectiveATest extends IntegrationTestConfiguration {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/objectiveA";
    }

    @Test
    public void name() {
        given()
                .queryParam("originStation", "BSB")
                .queryParam("destinyStation", "FLN")
                .queryParam("tripDate", "2021-02-10")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("origin", Matchers.is("BSB"))
                .body("destiny", Matchers.is("FLN"))
                .body("departure", Matchers.is("2021-02-10T09:00:00"))
                .body("arrival", Matchers.is("2021-02-10T19:20:00"))
                .body("steps[0].origin", Matchers.is("BSB"))
                .body("steps[0].destiny", Matchers.is("FLN"))
                .body("steps[0].departure", Matchers.is("2021-02-10T09:00:00"))
                .body("steps[0].arrival", Matchers.is("2021-02-10T19:20:00"))
                .body("steps[0].company", Matchers.is("UberOnRails"))
                .body("steps[0].price", Matchers.is(1347.21f))
                .statusCode(HttpStatus.OK.value());
    }

}

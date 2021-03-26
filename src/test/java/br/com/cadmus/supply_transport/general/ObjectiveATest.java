package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.IntegrationTestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ObjectiveATest extends IntegrationTestConfiguration {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/objectiveA";
    }

    @Test
    public void listing_ConnectionWithDate_ReturningFiltered() {
        given()
                .queryParam("originStation", "BSB")
                .queryParam("destinyStation", "FLN")
                .queryParam("tripDate", "2021-02-10")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(2))
                .body("$", everyItem(hasKey("origin")))
                .body("$", everyItem(hasKey("destiny")))
                .body("$", everyItem(hasKey("departure")))
                .body("$", everyItem(hasKey("arrival")))
                .body("$", everyItem(hasKey("steps")))
                .body("$.steps", everyItem(hasKey("origin")))
                .body("$.steps", everyItem(hasKey("destiny")))
                .body("$.steps", everyItem(hasKey("departure")))
                .body("$.steps", everyItem(hasKey("arrival")))
                .body("$.steps", everyItem(hasKey("company")))
                .body("$.steps", everyItem(hasKey("price")))
                .body("[0].origin", is("BSB"))
                .body("[0].destiny", is("FLN"))
                .body("[0].departure", is("2021-02-10T09:00:00"))
                .body("[0].arrival", is("2021-02-10T19:20:00"))
                .body("[0].steps[0].origin", is("BSB"))
                .body("[0].steps[0].destiny", is("FLN"))
                .body("[0].steps[0].departure", is("2021-02-10T09:00:00"))
                .body("[0].steps[0].arrival", is("2021-02-10T19:20:00"))
                .body("[0].steps[0].company", is("UberOnRails"))
                .body("[0].steps[0].price", is(1347.21f))
                .body("[1].origin", is("BSB"))
                .body("[1].destiny", is("FLN"))
                .body("[1].departure", is("2021-02-10T19:00:00"))
                .body("[1].arrival", is("2021-02-10T23:30:00"))
                .body("[1].steps[0].origin", is("BSB"))
                .body("[1].steps[0].destiny", is("FLN"))
                .body("[1].steps[0].departure", is("2021-02-10T19:00:00"))
                .body("[1].steps[0].arrival", is("2021-02-10T23:30:00"))
                .body("[1].steps[0].company", is("iTrain"))
                .body("[1].steps[0].price", is(369.19f))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listing_ConnectionWithNoDate_ReturningFiltered() {
        given()
                .queryParam("originStation", "PMW")
                .queryParam("destinyStation", "CGH")
                .queryParam("tripDate", "")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(4))
                .body("$", everyItem(hasKey("origin")))
                .body("$", everyItem(hasKey("destiny")))
                .body("$", everyItem(hasKey("departure")))
                .body("$", everyItem(hasKey("arrival")))
                .body("$", everyItem(hasKey("steps")))
                .body("$.steps", everyItem(hasKey("origin")))
                .body("$.steps", everyItem(hasKey("destiny")))
                .body("$.steps", everyItem(hasKey("departure")))
                .body("$.steps", everyItem(hasKey("arrival")))
                .body("$.steps", everyItem(hasKey("company")))
                .body("$.steps", everyItem(hasKey("price")))
                .body("[0].origin", is("PMW"))
                .body("[0].destiny", is("CGH"))
                .body("[0].departure", is("2021-02-10T09:40:00"))
                .body("[0].arrival", is("2021-02-10T19:40:00"))
                .body("[0].steps[0].origin", is("PMW"))
                .body("[0].steps[0].destiny", is("CGH"))
                .body("[0].steps[0].departure", is("2021-02-10T09:40:00"))
                .body("[0].steps[0].arrival", is("2021-02-10T19:40:00"))
                .body("[0].steps[0].company", is("UberOnRails"))
                .body("[0].steps[0].price", is(1199.58f))
                .body("[1].origin", is("PMW"))
                .body("[1].destiny", is("CGH"))
                .body("[1].departure", is("2021-02-11T09:40:00"))
                .body("[1].arrival", is("2021-02-11T19:40:00"))
                .body("[1].steps[0].origin", is("PMW"))
                .body("[1].steps[0].destiny", is("CGH"))
                .body("[1].steps[0].departure", is("2021-02-11T09:40:00"))
                .body("[1].steps[0].arrival", is("2021-02-11T19:40:00"))
                .body("[1].steps[0].company", is("UberOnRails"))
                .body("[1].steps[0].price", is(1199.58f))
                .body("[2].origin", is("PMW"))
                .body("[2].destiny", is("CGH"))
                .body("[2].departure", is("2021-02-12T09:40:00"))
                .body("[2].arrival", is("2021-02-12T19:40:00"))
                .body("[2].steps[0].origin", is("PMW"))
                .body("[2].steps[0].destiny", is("CGH"))
                .body("[2].steps[0].departure", is("2021-02-12T09:40:00"))
                .body("[2].steps[0].arrival", is("2021-02-12T19:40:00"))
                .body("[2].steps[0].company", is("UberOnRails"))
                .body("[2].steps[0].price", is(1199.58f))
                .body("[3].origin", is("PMW"))
                .body("[3].destiny", is("CGH"))
                .body("[3].departure", is("2021-02-17T09:40:00"))
                .body("[3].arrival", is("2021-02-17T19:40:00"))
                .body("[3].steps[0].origin", is("PMW"))
                .body("[3].steps[0].destiny", is("CGH"))
                .body("[3].steps[0].departure", is("2021-02-17T09:40:00"))
                .body("[3].steps[0].arrival", is("2021-02-17T19:40:00"))
                .body("[3].steps[0].company", is("UberOnRails"))
                .body("[3].steps[0].price", is(1199.58f))
                .statusCode(HttpStatus.OK.value());
    }

}

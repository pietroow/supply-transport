package br.com.cadmus.supply_transport;

import br.com.cadmus.supply_transport.IntegrationTestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ObjectiveBTest extends IntegrationTestConfiguration {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/stations/name";
    }

    @Test
    public void listing_EmptyString_ReturnAll() {
        given()
                .queryParam("stationName", "")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(10))
                .body("$", everyItem(hasKey("name")))
                .body("$", everyItem(hasKey("station")))
                .body("$", everyItem(hasKey("city")))
                .body("[0].name", is("Estação Eurico de Aguiar Salles"))
                .body("[0].station", is("VIX"))
                .body("[0].city", is("Vitória"))
                .body("[1].name", is("Estação Hercílio Luz"))
                .body("[1].station", is("FLN"))
                .body("[1].city", is("Florianópolis"))
                .body("[2].name", is("Estação Juscelino Kubitschek"))
                .body("[2].station", is("BSB"))
                .body("[2].city", is("Brasília"))
                .body("[3].name", is("Estação Zumbi dos Palmares"))
                .body("[3].station", is("MCZ"))
                .body("[3].city", is("Maceió"))
                .body("[4].name", is("Estação da Pampulha"))
                .body("[4].station", is("PLU"))
                .body("[4].city", is("Belo Horizonte"))
                .body("[5].name", is("Estação de Aracaju"))
                .body("[5].station", is("AJU"))
                .body("[5].city", is("Aracaju"))
                .body("[6].name", is("Estação de Belém de Cans"))
                .body("[6].station", is("BEL"))
                .body("[6].city", is("Belém"))
                .body("[7].name", is("Estação de Congonhas Paulo"))
                .body("[7].station", is("CGH"))
                .body("[7].city", is("São Paulo"))
                .body("[8].name", is("Estação de Palmas"))
                .body("[8].station", is("PMW"))
                .body("[8].city", is("Palmas"))
                .body("[9].name", is("Estação de Viracopos"))
                .body("[9].station", is("VCP"))
                .body("[9].city", is("Campinas"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listing_LowerCaseString_ReturnFiltered() {
        given()
                .queryParam("stationName", "al")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(3))
                .body("$", everyItem(hasKey("name")))
                .body("$", everyItem(hasKey("station")))
                .body("$", everyItem(hasKey("city")))
                .body("[0].name", is("Estação Eurico de Aguiar Salles"))
                .body("[0].station", is("VIX"))
                .body("[0].city", is("Vitória"))
                .body("[1].name", is("Estação Zumbi dos Palmares"))
                .body("[1].station", is("MCZ"))
                .body("[1].city", is("Maceió"))
                .body("[2].name", is("Estação de Palmas"))
                .body("[2].station", is("PMW"))
                .body("[2].city", is("Palmas"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listing_UpperCaseString_ReturnFiltered() {
        given()
                .queryParam("stationName", "AL")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(3))
                .body("$", everyItem(hasKey("name")))
                .body("$", everyItem(hasKey("station")))
                .body("$", everyItem(hasKey("city")))
                .body("[0].name", is("Estação Eurico de Aguiar Salles"))
                .body("[0].station", is("VIX"))
                .body("[0].city", is("Vitória"))
                .body("[1].name", is("Estação Zumbi dos Palmares"))
                .body("[1].station", is("MCZ"))
                .body("[1].city", is("Maceió"))
                .body("[2].name", is("Estação de Palmas"))
                .body("[2].station", is("PMW"))
                .body("[2].city", is("Palmas"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void listing_SpecialCharacter_ReturnEmpty() {
        given()
                .queryParam("stationName", "%")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("size()", is(0))
                .statusCode(HttpStatus.OK.value());
    }

}

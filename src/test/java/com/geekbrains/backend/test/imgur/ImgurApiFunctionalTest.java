package com.geekbrains.backend.test.imgur;

import java.util.Properties;

import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.is;

public class ImgurApiFunctionalTest extends FunctionalTest {


    private static Properties properties;
    private static String TOKEN;

    @BeforeAll
    static void beforeAll() throws Exception {
        properties = readProperties();
        RestAssured.baseURI = properties.getProperty("imgur-api-url");
        TOKEN = properties.getProperty("imgur-api-token");
    }

    @Test
    void getAccountBase() {
        String userName = "zdorka8686";

        RequestSpecification req = new RequestSpecBuilder()
                .build();

        new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();




        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.id", is(157817393))
                .log()
                .all()
                .when()
               .get("account/" + userName);
    }

    @Test
    void updateImageTest() {

        RequestSpecification req = new RequestSpecBuilder()
                .build();

        new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();
        given()
                .header("Authorization", "Client-ID 546c25a59c58ad7")
                .formParam("description", "Picture")
                .formParam("title", "new title >>")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
               .statusCode(200)
                .log()
                .all()
                .when()
                .post("image/j3uxNTdA2OrJXH6");
    }

    @Test
    void postImageTest() {

        RequestSpecification req = new RequestSpecBuilder()
                .build();

        new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("image", getFileResource("frog.png"))
                .formParam("name", "Picture")
                .formParam("title", "The best picture!")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.size", is(648957))
                .body("data.type", is("image/png"))
                .body("data.name", is("Picture"))
                 .body("data.title", is("The best picture!"))
                 .log()
                 .all()
                 .when()
                 .post("upload");
    }

    @Test
    void getImageTest() {
        RequestSpecification req = new RequestSpecBuilder()
                .build();

        new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();


        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .spec(responseSpecification);
                //.body("data.title", is("my frog"))
                //.log()
                //.all()
               // .when()
               // .get("image/Mxzxrkm");
    }


    @Test
    void deleteImageTest() {
        RequestSpecification req = new RequestSpecBuilder()
                .build();

        new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();


        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .spec(responseSpecification);
                //.statusCode(200)
               // .log()
               // .all()
               // .when()
               // .delete("image/j3uxNTdA2OrJXH6");
    }

    // TODO: 08.12.2021 Домашка протестировать через RA минимум 5 различных end point-ов
}

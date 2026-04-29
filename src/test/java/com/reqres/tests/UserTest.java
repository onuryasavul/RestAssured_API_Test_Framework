package com.reqres.tests;

// import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqres.base.BaseTest;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest{

    // @BeforeClass
    // public void setup() {
        // RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // RestAssured.basePath = "";
    // }

    @Test
    public void getUserList_shouldReturn200() {
    given(requestSpec)
    .when()
        .get("/users")
    .then()
        .statusCode(200)
        .body("size()", greaterThan(0))
        .body("[0].id", notNullValue());
}

    @Test
    public void getSingleUser_shouldReturn200() {
    given(requestSpec)
    .when()
        .get("/users/2")
    .then()
        .statusCode(200)
        .body("id", equalTo(2))
        .body("name", equalTo("Ervin Howell"))
        .body("email", notNullValue());
}

    @Test
    public void createUser_withRawJson() {
    
    String body = """
            {
            "name": "Onur",
            "username": "onury",
            "email": "onur@example.com"
            }
            """;

            ValidatableResponse res =
                given(requestSpec)
                    .body(body)
                .when()
                    .post("/users")
                .then()
                    .statusCode(anyOf(is(201), is(200)))
                    .contentType(containsString("application/json"))
                    .body("name", equalTo("Onur"))
                    .body("username", equalTo("onury"))
                    .body("email", equalTo("onur@example.com"))
                    .body("id", notNullValue());
        }



    // @Test
    // public void getSingleUser_notFound_shouldReturn404() {
    //     given()
    //     .when()
    //         .get("/users/999")
    //     .then()
    //         .statusCode(404);
    // }
}
package com.reqres.tests;

// import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqres.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest{

    // @BeforeClass
    // public void setup() {
        // Base URL'i bir kez set ediyoruz
        // RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // RestAssured.basePath = "";
    // }

    @Test(groups = {"demo1"})
    public void getUserList_shouldReturn200() {
    given()
    .when()
        .get("/users")
    .then()
        .statusCode(200)
        .body("size()", greaterThan(0))
        .body("[0].id", notNullValue());
}

    @Test(groups = {"demo2"})
    public void getSingleUser_shouldReturn200() {
    given()
    .when()
        .get("/users/2")
    .then()
        .statusCode(200)
        .body("id", equalTo(2))
        .body("email", notNullValue());
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
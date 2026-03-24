package br.com.dogapi.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseConfig {

    private static final String BASE_URL = "https://dog.ceo/api";

    private BaseConfig() {
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void setup() {
        RestAssured.requestSpecification = requestSpec();
    }
}
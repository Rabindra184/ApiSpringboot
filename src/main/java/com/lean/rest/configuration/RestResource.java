package com.lean.rest.configuration;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@Component
public class RestResource {

    @Autowired
    private SpecBuilder specBuilder;

    public Response post(String path, Object request) {
        return given(specBuilder.getRequestSpec()).body(request).when().post(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response post(String path, String token, Object request) {
        return given(specBuilder.getRequestSpec()).header("Authorization", "Bearer " + token).body(request).when().post(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response postAccount(String path, HashMap<String, String> formParams) {
        return given(specBuilder.getAccountRequestSpec()).formParams(formParams).when().post(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response get(String path, String token) {
        return given(specBuilder.getRequestSpec()).header("Authorization", "Bearer " + token).when().get(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response get(String path) {
        return given(specBuilder.getRequestSpec()).when().get(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response delete(String path, String token, Object body) {
        return given(specBuilder.getRequestSpec()).header("Authorization", "Bearer " + token).body(body).when().delete(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }

    public Response update(String path, String token, Object requestPlaylist) {
        return given(specBuilder.getRequestSpec()).header("Authorization", "Bearer " + token).body(requestPlaylist).when().put(path).then().spec(specBuilder.getResponseSpec()).extract().response();
    }
}

package com.lean.rest.configuration;


import com.lean.rest.annotation.LazyConfiguration;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class RequestConfiguration {
    @Value("${base.Url}")
    private String baseUrl;
    private RequestSpecification request;

    @Bean
    public RequestSpecification getRequestSpecification() {
        RestAssured.baseURI = this.baseUrl;
        this.request = RestAssured.given();
        this.request.header("Content-Type", "application/json");
        return request;
    }
}

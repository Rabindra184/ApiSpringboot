package com.lean.rest.configuration;

import com.lean.rest.annotation.LazyComponent;
import com.lean.rest.service.TokenManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public class SpecBuilder {

    @Value("${base.Url}")
    private String baseUrl;

    @Autowired
    private TokenManager tokenManager;

    public RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).addFilter(new AllureRestAssured()).log(LogDetail.ALL).build();
    }

    public RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).addFilter(new AllureRestAssured()).log(LogDetail.ALL).build();
    }

    public ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

}

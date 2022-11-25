package com.lean.rest.service;

import com.lean.rest.annotation.LazyComponent;
import com.lean.rest.apiEngine.apiservices.Route;
import com.lean.rest.apiEngine.requests.AuthorizationRequest;
import com.lean.rest.apiEngine.response.Token;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static com.lean.rest.apiEngine.apiservices.Route.*;

@LazyComponent
public class TokenManager {
    private String access_token;
    private Instant expiry_time;

    @Autowired
    private RequestSpecification request;
    @Autowired
    private AuthorizationRequest authRequest;

    public synchronized String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token ...");
                Token token = renewToken();
                access_token = token.getToken();
                //int expiryDurationInSeconds = Integer.parseInt(token.expires);
                //expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
                expiry_time = Instant.parse(token.getExpires()).minusSeconds(600);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Failed to get token");
        }
        return access_token;
    }

    private Token renewToken() {
        Response response = request.body(authRequest).post(ACCOUNT+BASE_PATH+GENERATETOKEN);
        if (response.statusCode() != HttpStatus.SC_OK)
            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response + " , Status Code : " + response.statusCode());
        return response.body().jsonPath().getObject("$", Token.class);
    }
}

package com.lean.rest.apiEngine.api;

import com.lean.rest.annotation.LazyComponent;
import com.lean.rest.apiEngine.apiservices.IRestResponse;
import com.lean.rest.apiEngine.apiservices.RestResponse;
import com.lean.rest.apiEngine.apiservices.Route;
import com.lean.rest.apiEngine.requests.AuthorizationRequest;
import com.lean.rest.apiEngine.response.UserAccount;
import com.lean.rest.configuration.RestResource;
import com.lean.rest.service.TokenManager;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import static com.lean.rest.apiEngine.apiservices.Route.Authorized;
import static com.lean.rest.apiEngine.apiservices.Route.USER;


@LazyComponent
public class AccountApi {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private RestResource restResource;

    public Response post(AuthorizationRequest request) {
        return restResource.post(USER, request);
    }

    public Response post(Object request) {
        return restResource.post(Authorized, request);
    }

    public IRestResponse<UserAccount> getUserAccount(String userId) {
        Response response = restResource.get(Route.userAccount(userId), tokenManager.getToken());
        return new RestResponse<>(UserAccount.class, response);
    }

}

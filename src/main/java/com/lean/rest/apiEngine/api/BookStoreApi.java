package com.lean.rest.apiEngine.api;

import com.lean.rest.annotation.LazyComponent;
import com.lean.rest.apiEngine.apiservices.IRestResponse;
import com.lean.rest.apiEngine.apiservices.RestResponse;
import com.lean.rest.apiEngine.apiservices.Route;
import com.lean.rest.apiEngine.requests.AddBooksRequest;
import com.lean.rest.apiEngine.requests.RemoveBookRequest;
import com.lean.rest.apiEngine.response.Books;
import com.lean.rest.apiEngine.response.UserAccount;
import com.lean.rest.configuration.RestResource;
import com.lean.rest.service.TokenManager;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lean.rest.apiEngine.apiservices.Route.*;

@LazyComponent
public class BookStoreApi {
    @Autowired
    private RestResource restResource;
    @Autowired
    private TokenManager tokenManager;

    public IRestResponse<Books> getBooks() {
        Response response = restResource.get(BOOKSTORE + BASE_PATH + BOOKS);
        return new RestResponse<>(Books.class, response);
    }

    public IRestResponse<Books> addBook(AddBooksRequest addBooksRequest) {
        Response response = restResource.post(BOOKSTORE + BASE_PATH + BOOKS, tokenManager.getToken(), addBooksRequest);
        return new RestResponse<>(Books.class, response);
    }

    public Response removeBook(RemoveBookRequest removeBookRequest) {
        return restResource.delete(BOOKSTORE + BASE_PATH + BOOK, tokenManager.getToken(), removeBookRequest);
    }

    public IRestResponse<UserAccount> getUserAccount(String USER_ID) {
        Response response = restResource.get(Route.userAccount(USER_ID), tokenManager.getToken());
        return new RestResponse<>(UserAccount.class, response);
    }
}

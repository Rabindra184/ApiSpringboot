package com.lean.rest.stepDefinitions;

import com.lean.rest.apiEngine.apiservices.IRestResponse;
import com.lean.rest.apiEngine.model.Book;
import com.lean.rest.apiEngine.response.Books;
import com.lean.rest.apiEngine.response.UserAccount;
import com.lean.rest.context.TestContext;
import com.lean.rest.enums.Context;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerificationSteps extends BaseStep {

    public VerificationSteps(TestContext testContext) {
        super(testContext);
    }


    @Then("the book is added")
    public void bookIsAdded() {
        System.out.println(scenarioContext);
        Book book = (Book) scenarioContext.getContext(Context.BOOK);
        System.out.println(book.getIsbn());
        IRestResponse<Books> BooksRespon = (IRestResponse<Books>) scenarioContext.getContext(Context.BOOKS);
        Assert.assertEquals(201, BooksRespon.getStatusCode());
        Assert.assertTrue(BooksRespon.isSuccessful());
        Assert.assertEquals(book.getIsbn(), BooksRespon.getBody().books.get(0).getIsbn());
    }

    @Then("the book is removed")
    public void bookIsRemoved() {
        System.out.println(scenarioContext);
        String userId = (String) scenarioContext.getContext(Context.USER_ID);
        System.out.println(userId);
        Response response = (Response) scenarioContext.getContext(Context.BOOK_REMOVE_RESPONSE);
        Assert.assertEquals(response.getStatusCode(),204 );
        IRestResponse<UserAccount> userAccountResponse = accountApi.getUserAccount(userId);
        Assert.assertEquals(200, userAccountResponse.getStatusCode());
        Assert.assertEquals(0, userAccountResponse.getBody().getBooks().size());
    }


}
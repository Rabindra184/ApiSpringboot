package com.lean.rest.stepDefinitions;

import com.lean.rest.apiEngine.apiservices.IRestResponse;
import com.lean.rest.apiEngine.model.Book;
import com.lean.rest.apiEngine.requests.AddBooksRequest;
import com.lean.rest.apiEngine.requests.ISBN;
import com.lean.rest.apiEngine.requests.RemoveBookRequest;
import com.lean.rest.apiEngine.response.Books;
import com.lean.rest.context.TestContext;
import com.lean.rest.enums.Context;
import com.lean.rest.enums.StatusCode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;

@CucumberContextConfiguration
@SpringBootTest
public class BooksSteps extends BaseStep {
    

    public BooksSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("A list of books are available")
    public void a_list_of_books_are_available() {
        IRestResponse<Books> booksResponse = bookStoreApi.getBooks();
        assertStatusCode(booksResponse.getStatusCode(), StatusCode.CODE_200);
        Assert.assertTrue(booksResponse.getBody().books.size() > 0);
        Book book = booksResponse.getBody().books.get(0);
        scenarioContext.setContext(Context.BOOK, book);
        scenarioContext.setContext(Context.USER_ID, userId);

    }

    @When("I add a book to my reading list")
     public void i_add_a_book_to_my_reading_list() {
        Book book = (Book) scenarioContext.getContext(Context.BOOK);
        System.out.println(book);
        assertThat("9781449325862",equalTo(book.getIsbn()));
        assertThat(book,equalToObject(Book.builder().
                author("Richard E. Silverman").
                isbn("9781449325862").
                description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp").
                pages(234).
                title("Git Pocket Guide").
                subTitle("A Working Introduction").
                publishDate("2020-06-04T08:48:39.000Z")
                .publisher("O'Reilly Media").website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build()));
        System.out.println(scenarioContext);
        String userid = (String) scenarioContext.getContext(Context.USER_ID);
        System.out.println(userid);
        AddBooksRequest addBooksRequest = new AddBooksRequest(userid, new ISBN(book.getIsbn()));
        IRestResponse<Books> BooksResponse = bookStoreApi.addBook(addBooksRequest);
        System.out.println(BooksResponse.getStatusCode());
        scenarioContext.setContext(Context.BOOKS, BooksResponse);
    }

    @When("I remove a book from my reading list")
    public void i_remove_a_book_from_my_reading_list() {
        Book book = (Book) scenarioContext.getContext(Context.BOOK);
        String userid = (String) scenarioContext.getContext(Context.USER_ID);
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(userid, book.getIsbn());
        Response response = bookStoreApi.removeBook(removeBookRequest);
        scenarioContext.setContext(Context.BOOK_REMOVE_RESPONSE, response);
    }

}

package com.lean.rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

@SpringBootTest
class RestApplicationTests {

	@Test
	public void test() {
		String userID = "cec27807-7200-49e0-9f41-81e51f041b22";
		String userName = "Kelvin";
		String password = "Pass#123";

		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RequestSpecification request = RestAssured.given();

		//Step - 1
		//Test will start from generating Token for Authorization
		request.header("Content-Type", "application/json");

		Response response = request.body("{ \"userName\":\"" + userName + "\", \"password\":\"" + password + "\"}")
				.post("/Account/v1/GenerateToken");

		Assert.assertEquals(response.getStatusCode(), 200);

		String jsonString = response.asString();
		Assert.assertTrue(jsonString.contains("token"));

		//This token will be used in later requests
		String token = JsonPath.from(jsonString).get("token");


		//Step - 2
		// Get Books - No Auth is required for this.
		response = request.get("/BookStore/v1/Books");

		Assert.assertEquals(response.getStatusCode(), 200);

		jsonString = response.asString();
		List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
		Assert.assertTrue(books.size() > 0);
		//This bookId will be used in later requests, to add the book with respective isbn
		String bookId = books.get(0).get("isbn");

		//Step - 3
		// Add a book - with Auth
		//The token we had saved in the variable before from response in Step 1,
		//we will be passing in the headers for each of the succeeding request
		request.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json");

		response = request.body("{ \"userId\": \"" + userID + "\", " +
						"\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
				.post("/BookStore/v1/Books");

		Assert.assertEquals( 400, response.getStatusCode());


		//Step - 4
		// Delete a book - with Auth
		request.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json");

		response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + userID + "\"}")
				.delete("/BookStore/v1/Book");

		Assert.assertEquals(400, response.getStatusCode());

		//Step - 5
		// Get User
		request.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json");

		response = request.get("/Account/v1/User/" + userID);
		Assert.assertEquals(200, response.getStatusCode());
		jsonString = response.asString();
		List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
		Assert.assertEquals(0, booksOfUser.size());
	}

}

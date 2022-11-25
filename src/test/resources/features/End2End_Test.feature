Feature: End To test for BookStore API
  Description:The purpose of these tests are to test End to end happy flows for customer

  Booksore Swagger URL:https://demoqa.com/swagger/


  Scenario: The Authorized user can Add and Remove a book.
    Given  A list of books are available
    When I add a book to my reading list
    Then the book is added
    When I remove a book from my reading list
    Then the book is removed

package com.lean.rest.apiEngine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lean.rest.annotation.LazyComponent;
import lombok.*;

import java.util.Objects;

@Builder
@Data
@LazyComponent
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"isbn", "title", "subTitle", "author", "publish_date", "publisher", "pages", "description", "website"})

public class Book {

    @JsonProperty("isbn")
    String isbn;
    @JsonProperty("title")
    String title;
    @JsonProperty("subTitle")
    String subTitle;
    @JsonProperty("author")
    String author;
    @JsonProperty("publish_date")
    String publishDate;
    @JsonProperty("publisher")
    String publisher;
    @JsonProperty("pages")
    Integer pages;
    @JsonProperty("description")
    String description;
    @JsonProperty("website")
    String website;

}

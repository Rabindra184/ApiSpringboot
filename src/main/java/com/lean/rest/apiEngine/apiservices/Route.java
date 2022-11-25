package com.lean.rest.apiEngine.apiservices;

public class Route {
    public static final String BOOKSTORE = "/BookStore";
    public static final String ACCOUNT = "/Account";
    public static final String BASE_PATH = "/v1";
    public static final String GENERATETOKEN = "/GenerateToken";
    public static final String BOOKS = "/Books";
    public static final String BOOK = "/Book";
    public static final String USER = "/User";
    public static final String Authorized = "/Authorized";
    public static String userAccount(String userId) {
        return ACCOUNT + BASE_PATH + USER + "/" + userId;
    }

}

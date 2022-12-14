package com.lean.rest.apiEngine.requests;

import com.lean.rest.annotation.LazyComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class AddBooksRequest {

    public String userId;
    public List<ISBN> collectionOfIsbns = null;


    public AddBooksRequest(String userId, ISBN isbn) {
        this.userId = userId;
        collectionOfIsbns = new ArrayList<ISBN>();
        collectionOfIsbns.add(isbn);
    }

}

package com.lean.rest.apiEngine.requests;

import com.lean.rest.annotation.LazyComponent;
import org.springframework.beans.factory.annotation.Autowired;

@LazyComponent
public class ISBN {

	public String isbn;
	public ISBN(String isbn) {
        this.isbn = isbn;
    }
}

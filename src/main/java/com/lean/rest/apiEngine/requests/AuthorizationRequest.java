package com.lean.rest.apiEngine.requests;

import com.lean.rest.annotation.LazyComponent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
@Data
@LazyComponent
public class AuthorizationRequest {
    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;

}



package com.lean.rest.stepDefinitions;

import com.lean.rest.apiEngine.api.AccountApi;
import com.lean.rest.apiEngine.requests.AuthorizationRequest;
import com.lean.rest.context.TestContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountSteps extends BaseStep {

    @Autowired
    private AuthorizationRequest credentials;

    @Autowired
    private AccountApi accountApi;

    public AccountSteps(TestContext testContext) {
        super(testContext);
    }


}

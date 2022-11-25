package com.lean.rest.stepDefinitions;
import com.lean.rest.apiEngine.api.AccountApi;
import com.lean.rest.apiEngine.api.BookStoreApi;
import com.lean.rest.context.ScenarioContext;
import com.lean.rest.context.TestContext;
import com.lean.rest.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BaseStep {

    @Autowired
    public ScenarioContext scenarioContext;
    @Autowired
    public BookStoreApi bookStoreApi;
    @Autowired
    public AccountApi accountApi;
    @Value("${userId}")
    public String userId;

    public BaseStep(TestContext testContext)  {
     scenarioContext=testContext.getScenarioContext();
    }

    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

}

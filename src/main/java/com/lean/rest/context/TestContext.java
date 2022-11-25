package com.lean.rest.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class TestContext {

    private ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();

    }
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}

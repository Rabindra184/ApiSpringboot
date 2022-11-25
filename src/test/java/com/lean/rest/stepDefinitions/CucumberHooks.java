package com.lean.rest.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class CucumberHooks {


    @AfterStep
    public void afterStep(Scenario scenario) {


        if (scenario.isFailed()) {

        }
    }


    @BeforeStep
    public void beforeStep(Scenario scenario) {

    }

    @After
    public void afterScenario(Scenario scenario) {

    }

}

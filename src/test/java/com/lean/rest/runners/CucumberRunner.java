package com.lean.rest.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:features",
         glue =  "com.lean.rest.stepDefinitions",
//        tags =  "@visa",
        plugin = {
                "pretty",
                "html:target/cucumber/report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

@SpringBootTest
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

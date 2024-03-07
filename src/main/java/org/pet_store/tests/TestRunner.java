package org.pet_store.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "org.pet_store.steps",
        tags = "@Regression",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}

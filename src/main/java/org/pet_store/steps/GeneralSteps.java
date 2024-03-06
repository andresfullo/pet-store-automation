package org.pet_store.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.Assert;
import org.pet_store.utils.ConfigurationManager;
import org.pet_store.utils.GlobalVariables;

public class GeneralSteps {

    @When("the URL is {string}")
    public void theUserURLIsUserURL(String userURL) {
        RestAssured.baseURI = ConfigurationManager.getBaseURL() + userURL;
        RestAssured.config = RestAssured.config().sslConfig(
                new SSLConfig().relaxedHTTPSValidation()
        );
    }

    @Then("a response code of {int} is expected")
    public void aResponseCodeOfIsExpected(int statusCode) {
        GlobalVariables.response.getBody().print();
        Assert.assertEquals(statusCode, GlobalVariables.response.statusCode());
    }

}

package org.pet_store.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.pet_store.dto.UserDTO;
import org.pet_store.utils.GlobalVariables;

public class UserSteps {

    private UserDTO user;

    @Given("a user is created with {string}, {string}, {string}, {string}, {string}, {string}, {int}")
    public void aUserIsCreatedWith(String username, String firstName, String lastName, String email,
                                   String password, String phone, int userStatus){

        user = new UserDTO(-1, username, firstName, lastName, email, password, phone, userStatus);
        String requestBody = user.toJson(false);

        GlobalVariables.response = RestAssured.given().contentType("application/json").body(requestBody).post();
    }

    @Given("the user data {string} {string}")
    public void theUserData(String username, String password) {
        RestAssured.baseURI += "?username=" + username + "&password=" + password;
        GlobalVariables.response = RestAssured.get();
    }

    @And("the response user is valid")
    public void theResponseUserIsValid() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO respUser = objectMapper.readValue(GlobalVariables.response.getBody().asString(), UserDTO.class);
        Assert.assertTrue(user.equals(respUser));
        Assert.assertTrue(respUser.getId() >= 0);
    }

    @Given("the username {string}")
    public void theUsername(String username) {
        RestAssured.baseURI += "/" + username;
    }

    @And("the user info should be valid")
    public void theUserInfoShouldBeValid() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO respUser = objectMapper.readValue(GlobalVariables.response.getBody().asString(), UserDTO.class);
        Assert.assertTrue(respUser.isValid());
    }

    @And("update the user data {string} {string} {string} {string} {string} {string} {int}")
    public void updateTheUserData(String username, String firstName, String lastName, String email,
                                  String password, String phone, int userStatus) {
        user = new UserDTO(0, username, firstName, lastName, email, password, phone, userStatus);
        String requestBody = user.toJson(true);

        GlobalVariables.response = RestAssured.given().contentType("application/json").body(requestBody).put();
    }


    @And("user is deleted")
    public void userIsDeleted() {
        GlobalVariables.response = RestAssured.delete();
    }

    @And("user is searched")
    public void userIsSearched() {
        GlobalVariables.response = RestAssured.get();
    }
}

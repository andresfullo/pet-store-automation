package org.pet_store.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.pet_store.dto.CategoryDTO;
import org.pet_store.dto.PetDTO;
import org.pet_store.dto.UserDTO;
import org.pet_store.utils.GlobalVariables;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class PetSteps {

    private PetDTO pet;

    @Given("the pet data {string} {string} {string} {string} {string}")
    public void thePetData(String name, String category, String photoUrls, String tags, String status){
        String[] arrCat = category.split(",");
        CategoryDTO categoryDTO = new CategoryDTO(Integer.parseInt(arrCat[0]), arrCat[1]);
        pet = new PetDTO(0, name, categoryDTO, status);
        pet.addPhotosURLs(photoUrls.split(","));
        pet.addTags(tags.split(";"));

        String jsonBoy = pet.toJson(true);
        System.out.println(jsonBoy);

        GlobalVariables.response = RestAssured.given()
                .contentType("application/json").body(jsonBoy).post();
    }

    @And("response pet is consistent with data")
    public void responsePetIsConsistentWithData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        GlobalVariables.response.getBody().print();
        PetDTO respPet = objectMapper.readValue(GlobalVariables.response.getBody().asString(), PetDTO.class);
        Assert.assertTrue(pet.equals(respPet));
        Assert.assertTrue(respPet.getId() >= 0);
    }

    @Given("the pet id {int}")
    public void thePetId(int petId) {
        RestAssured.baseURI += "/" + petId;
        GlobalVariables.response = RestAssured.get();
    }

    @And("response pet is valid")
    public void responsePetIsValid() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PetDTO respPet = objectMapper.readValue(GlobalVariables.response.getBody().asString(), PetDTO.class);
        Assert.assertTrue(respPet.isValid());
    }


    @Given("the pet id {int} and image {string}")
    public void thePetIdAndImage(int petId, String imageUrl) throws IOException {
        File imageFile = new File(imageUrl);

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        //RestAssured.baseURI += "/" + petId + "/uploadImage?additionalMetadata=" + imageFile.getName();
        RestAssured.baseURI = "";
        System.out.println(RestAssured.baseURI);
        GlobalVariables.response = RestAssured.given()
                .contentType(ContentType.BINARY)
                .body(imageFile)
                .when()
                .post("http://localhost:8080/api/v3/pet/1/uploadImage?additionalMetadata=Golden-Retriever-Puppy.jpg");
    }

    @And("the {string} is in the response")
    public void theIsInTheResponse(String urlImage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PetDTO respPet = objectMapper.readValue(GlobalVariables.response.getBody().asString(), PetDTO.class);
        Assert.assertTrue(respPet.isUrlImagePresent(urlImage));
    }
}

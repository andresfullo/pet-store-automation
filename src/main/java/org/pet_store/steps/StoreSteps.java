package org.pet_store.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.pet_store.dto.OrderDTO;
import org.pet_store.dto.StoreInventoryDTO;
import org.pet_store.dto.UserDTO;
import org.pet_store.utils.GlobalVariables;
import org.pet_store.utils.ObjectMapperUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StoreSteps {

    private StoreInventoryDTO storeInventory;
    private OrderDTO order;

    @Given("get the inventory data")
    public void getTheInventoryData(){
        GlobalVariables.response = RestAssured.get();
    }

    @And("the inventory data is valid")
    public void theInventoryDataIsValid() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        StoreInventoryDTO storeInv = objectMapper.readValue(
                GlobalVariables.response.getBody().asString(), StoreInventoryDTO.class);
        Assert.assertTrue(storeInv.isValid());
    }

    @Given("the order data {int} {int} {string} {string} {string}")
    public void theOrderDataPetIdQuantity(int petId, int quantity, String shipDate, String status, String complete) {
        order = new OrderDTO(-1, petId, quantity, shipDate, status, complete);
        String requestBody = order.toJson(false);
        System.out.println(requestBody);

        GlobalVariables.response = RestAssured.given().contentType("application/json").body(requestBody).post();
    }


    @And("response order is consistent with data")
    public void responseOrderIsConsistentWithData() throws JsonProcessingException {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ObjectMapper objectMapper = ObjectMapperUtils.prepareObjectMapper(customFormatter);

        OrderDTO respOrder = objectMapper.readValue(GlobalVariables.response.getBody().asString(), OrderDTO.class);
        Assert.assertTrue(order.equals(respOrder));
        Assert.assertTrue(respOrder.getId() >= 0);
    }

    @Given("the order id {int}")
    public void theOrderId(int orderId) {
        RestAssured.baseURI += "/" + orderId;
        GlobalVariables.response = RestAssured.get();
    }

    @And("response order is valid")
    public void responseOrderIsValid() throws JsonProcessingException {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ObjectMapper objectMapper = ObjectMapperUtils.prepareObjectMapper(customFormatter);

        OrderDTO respOrder = objectMapper.readValue(GlobalVariables.response.getBody().asString(), OrderDTO.class);
        Assert.assertTrue(respOrder.isValid());
    }
}

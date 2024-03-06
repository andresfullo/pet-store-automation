package org.pet_store.dto;

import org.pet_store.utils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDTO {

    private int id;
    private int petId;
    private int quantity;
    private LocalDateTime shipDate;
    private String status;
    private boolean complete;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public OrderDTO() {

    }

    public OrderDTO(int id, int petId, int quantity, LocalDateTime shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public OrderDTO(int id, int petId, int quantity, String shipDate, String status, String complete) {

        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = LocalDateTime.parse(shipDate, formatter);
        this.status = status;
        this.complete = complete.equals("true");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String toJson(boolean useId){
        return "{"
                + (useId ? " \"id\": " + id: "")
                + "\"petId\": " + petId
                + ", \"quantity\": " + quantity
                + ", \"shipDate\": \"" + shipDate.format(formatter)
                + "\", \"status\": \"" + status
                + "\", \"complete\": " + (complete ? "true" : "false")
                + "}";
    }

    public boolean equals(OrderDTO order){
        return petId == order.getPetId()
                && quantity == order.getQuantity()
                && shipDate.equals(order.shipDate)
                && status.equals(order.status)
                && complete == order.complete;
    }

    public boolean isValid(){
        return id >= 0
                && petId >= 0
                && shipDate != null
                && !StringUtils.isNullOrEmpty(status);
    }

}

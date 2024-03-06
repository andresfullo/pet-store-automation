package org.pet_store.dto;

public class StoreInventoryDTO {

    private int approved;
    private int placed;
    private int delivered;

    public StoreInventoryDTO() {
        this.approved = 0;
        this.placed = 0;
        this.delivered = 0;
    }

    public StoreInventoryDTO(int approved, int placed, int delivered) {
        this.approved = approved;
        this.placed = placed;
        this.delivered = delivered;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getPlaced() {
        return placed;
    }

    public void setPlaced(int placed) {
        this.placed = placed;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public boolean isValid(){
        return (approved >=0 && placed >=0 && delivered >=0 ) && (
                approved > 0 || placed > 0 || delivered > 0);
    }
}

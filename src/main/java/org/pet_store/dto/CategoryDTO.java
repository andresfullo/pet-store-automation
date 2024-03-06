package org.pet_store.dto;

public class CategoryDTO {

    private int id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(CategoryDTO category){
        return id == category.id
                && name.equals(category.getName());
    }

    public String toJson(){
        return "{\"id\": " + id
                + ", \"name\": \"" + name + "\"}";
    }
}

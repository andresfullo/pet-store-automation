package org.pet_store.dto;

public class TagDTO {

    private int id;
    private String name;

    public TagDTO() {
    }
    public TagDTO(int id, String name) {
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

    public boolean equals(TagDTO tag){
        return id == tag.id
                && name.equals(tag.getName());
    }

    public String toJson(){
        return "{\"id\": " + id
                + ", \"name\": \"" + name + "\"}";
    }

}

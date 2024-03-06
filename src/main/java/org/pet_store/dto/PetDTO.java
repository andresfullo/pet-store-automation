package org.pet_store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.pet_store.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PetDTO {

    private int id;
    private String name;
    private CategoryDTO category;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<TagDTO> tags;
    private String status;

    public PetDTO() {
    }

    public PetDTO(int id, String name, CategoryDTO category, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.photoUrls = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public void addPhotoUrl(String url){
        this.photoUrls.add(url);
    }

    public void addPhotosURLs(String[] urls){
        this.photoUrls.addAll(List.of(urls));
    }

    public void addTag(TagDTO tag){
        this.tags.add(tag);
    }

    public void addTags(String[] strTags){
        for(String strTag : strTags){
            String[] arrTag = strTag.split(",");
            TagDTO tag = new TagDTO(Integer.parseInt(arrTag[0]), arrTag[1]);
            this.tags.add(tag);
        }
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String photosToJsonList(){
        StringBuilder jsonList = new StringBuilder("[");
        int photosSize = photoUrls.size();
        for(int i=0; i<photosSize; i++){
            jsonList.append("\"").append(photoUrls.get(i)).append("\"").append((photosSize - 1) > i ? ", " : " ");
        }

        jsonList.append("]");
        return jsonList.toString();
    }

    public boolean isUrlImagePresent(String imageUrl){
        return photoUrls.contains(imageUrl);
    }

    public String tagsToJsonString(){
        StringBuilder jsonList = new StringBuilder("[");
        int tagsSize = tags.size();
        for(int i=0; i<tagsSize; i++){
            jsonList.append(tags.get(i).toJson()).append((tagsSize - 1) > i ? "," : "");
        }
        jsonList.append("]");
        return jsonList.toString();
    }

    public String toJson(boolean useId){
        return "{ " + (useId ? " \"id\": " + id + ", ": "")
                + "\"name\": \"" + name + "\", "
                + "\"category\": " + category.toJson() + ", "
                + "\"photoUrls\": " + photosToJsonList() + ", "
                + "\"tags\": " + tagsToJsonString() + ", "
                + "\"status\": \"" + status + "\""
                + "}";
    }

    public boolean areTagsEqual(List<TagDTO> tagDTOS){
        if(tags.size() != tagDTOS.size())
            return false;

        for(int i=0; i<tags.size(); i++){
            if(!tags.get(i).equals(tagDTOS.get(i)))
                return false;
        }

        return true;

    }

    public boolean equals(PetDTO pet){

        return this.name.equals(pet.getName())
                && this.category.equals(pet.getCategory())
                && this.photoUrls.equals(pet.photoUrls)
                && this.areTagsEqual(pet.tags)
                && this.status.equals(pet.getStatus());
    }

    public boolean isValid(){
        return this.id >= 0
                && !StringUtils.isNullOrEmpty(this.name)
                && this.category != null
                && !this.photoUrls.isEmpty()
                && !this.tags.isEmpty()
                && !StringUtils.isNullOrEmpty(this.status);
    }

}

package org.pet_store.dto;

import org.pet_store.utils.StringUtils;

public class UserDTO {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public UserDTO() {
        this.id = -1;
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.phone = "";
        this.userStatus = 1;
    }

    public UserDTO(
            int id, String username, String firstName, String lastName, String email,
            String password, String phone, int userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public boolean equals(UserDTO user){
        return this.username.equals(user.getUsername())
                && this.firstName.equals(user.getFirstName())
                && this.lastName.equals(user.getLastName())
                && this.email.equals(user.getEmail())
                && this.password.equals(user.getPassword())
                && this.phone.equals(user.getPhone())
                && this.userStatus == user.getUserStatus();
    }

    public String toJson(boolean useId){
        return  "{"
                + (useId ? "\"id\": " + id + ",": "")
                + "\"username\": \"" + username
                + "\", \"firstName\": \"" + firstName
                + "\", \"lastName\": \"" + lastName
                + "\", \"email\": \"" + email
                + "\", \"password\": \"" + password
                + "\", \"phone\": \"" + phone
                + "\", \"userStatus\": " + userStatus + " }";
    }

    public boolean isValid(){
        return id >= 0
                && !StringUtils.isNullOrEmpty(username)
                && !StringUtils.isNullOrEmpty(firstName)
                && !StringUtils.isNullOrEmpty(lastName)
                && !StringUtils.isNullOrEmpty(email)
                && !StringUtils.isNullOrEmpty(password)
                && !StringUtils.isNullOrEmpty(phone)
                && userStatus >= 0;
    }
}

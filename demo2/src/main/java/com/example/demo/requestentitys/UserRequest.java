package com.example.demo.requestentitys;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest
{
    @JsonProperty("id")
    int id;

    @JsonProperty("name")
    String name;

    @JsonProperty("email")
    String email;

    @JsonProperty("hashcode")
    String hashcode;

    @JsonProperty("iconname")
    String iconname;

    public UserRequest() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }
}

package com.example.demo.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Users
{
    @Id
    private int id;
    private String name;
    private String email;
    private String hashcode;
    private String iconname;

    public Users(){}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hashcode='" + hashcode + '\'' +
                ", iconname='" + iconname + '\'' +
                '}';
    }
}

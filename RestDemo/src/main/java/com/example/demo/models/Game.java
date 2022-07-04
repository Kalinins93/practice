package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "games")
public class Game
{
    @Id
    private int id;
    private String title;
    private String description;
    private Integer price;
    private String imagename;

    public Game(){}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public String getImagename()
    {
        return imagename;
    }

    public void setImagename(String imagename)
    {
        this.imagename = imagename;
    }
}

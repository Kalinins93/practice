package com.example.demo.models;

import org.springframework.data.annotation.Id;

public class Games
{
    @Id
    private int id;
    private String title;
    private String description;
    private Integer price;
    private String imagename;
    //private List<String> genres;

    public Games(int id, String title, String description, Integer price, String imagename)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagename = imagename;
    }

    /*
        public Game(int id, String title, String description, int price, String ... genres)
        {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
            this.genres = Arrays.asList(genres);
        }
    */
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
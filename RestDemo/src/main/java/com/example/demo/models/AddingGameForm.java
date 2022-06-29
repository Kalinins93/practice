package com.example.demo.models;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class AddingGameForm
{
    private String title;
    private String description;
    private Integer price;
    private MultipartFile poster;
    private List<MultipartFile > screenshots;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public MultipartFile  getPoster() {
        return poster;
    }

    public void setPoster(MultipartFile  poster) {
        this.poster = poster;
    }

    public List<MultipartFile > getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<MultipartFile > screenshots) {
        this.screenshots = screenshots;
    }
}

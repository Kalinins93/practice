package com.example.demo.models;

import org.springframework.data.annotation.Id;

public class Screenshot
{
    @Id
    private int id;
    private int idofgame;
    private String screenshotname;

    public Screenshot(int id, int idofgame, String screenshotname)
    {
        this.id = id;
        this.idofgame = idofgame;
        this.screenshotname = screenshotname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdofgame() {
        return idofgame;
    }

    public void setIdofgame(int idofgame) {
        this.idofgame = idofgame;
    }

    public String getScreenshotname() {
        return screenshotname;
    }

    public void setScreenshotname(String screenshotname) {
        this.screenshotname = screenshotname;
    }
}

package com.example.mobileprogrammingproject;

public class resturantrecycle {

    String title;
     String image;

    public  String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public resturantrecycle(String title, String image) {
        this.image=image;
        this.title=title;
    }
}

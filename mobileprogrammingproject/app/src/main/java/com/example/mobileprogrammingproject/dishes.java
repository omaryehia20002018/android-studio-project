package com.example.mobileprogrammingproject;

public class dishes  {
    String title1;
    String title2;
    String title3;


    public String getTitle3() {
        return title3;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    String image1;

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public dishes(String title1 , String title2,String title3, String image1) {
        this.title1 = title1;

        this.title2 = title2;

        this.title3 = title3;

        this.image1 = image1;

    }
}

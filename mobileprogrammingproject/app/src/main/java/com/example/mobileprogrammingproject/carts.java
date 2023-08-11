package com.example.mobileprogrammingproject;

public class carts {
    String order;
    String money;

    String dishimg;

    public String getDishimg() {
        return dishimg;
    }

    public void setDishimg(String dishimg) {
        this.dishimg = dishimg;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }



    public carts(String order,String money, String dishimg) {
        this.order = order;
        this.money=money;

        this.dishimg=dishimg;
    }
}

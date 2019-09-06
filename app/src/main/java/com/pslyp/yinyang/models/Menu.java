package com.pslyp.yinyang.models;

public class Menu {

    private String name;
    private String num_yhin;
    private String num_yhang;
    private String category;
    private String ingredient;
    private String howto;
    private String image;

    public Menu(String name, String num_yhin, String num_yhang, String category, String ingredient, String howto, String image) {
        this.name = name;
        this.num_yhin = num_yhin;
        this.num_yhang = num_yhang;
        this.category = category;
        this.ingredient = ingredient;
        this.howto = howto;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getYhin() {
        return num_yhin;
    }

    public String getYhang() {
        return num_yhang;
    }

    public String getCategory() {
        return category;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getHowto() {
        return howto;
    }

    public String getImage() {
        return image;
    }

}
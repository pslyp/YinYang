package com.pslyp.yinyang.models;

public class Menu {

    private String id;
    private String name;
    private String num_yhin;
    private String num_yhang;
    private String category;
    private String ingredient;
    private String howto;
    private String image;
    private String favorite;

    public Menu(String id, String name, String num_yhin, String num_yhang, String category, String ingredient, String howto, String image, String favorite) {
        this.id = id;
        this.name = name;
        this.num_yhin = num_yhin;
        this.num_yhang = num_yhang;
        this.category = category;
        this.ingredient = ingredient;
        this.howto = howto;
        this.image = image;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
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

    public String getFavorite() {
        return favorite;
    }
}
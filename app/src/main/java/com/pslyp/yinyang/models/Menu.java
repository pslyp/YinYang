package com.pslyp.yinyang.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {

    private String id;
    private String name;
    private String num_yhin;
    private String num_yhang;
    private String category;
    private String ingredient;
    private String howto;
    private String image;
    private int favorite;

    public Menu(String id, String name, String num_yhin, String num_yhang, String category, String ingredient, String howto, String image, int favorite) {
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

    protected Menu(Parcel in) {
        id = in.readString();
        name = in.readString();
        num_yhin = in.readString();
        num_yhang = in.readString();
        category = in.readString();
        ingredient = in.readString();
        howto = in.readString();
        image = in.readString();
        favorite = in.readInt();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

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

    public int getFavorite() {
        return favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(num_yhin);
        parcel.writeString(num_yhang);
        parcel.writeString(category);
        parcel.writeString(ingredient);
        parcel.writeString(howto);
        parcel.writeString(image);
        parcel.writeInt(favorite);
    }
}
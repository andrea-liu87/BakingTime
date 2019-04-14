package com.andrea.com.bakingtime.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    private  int id;
    private String name;
    private Ingredients[] ingredients;
    private Steps[] steps;
    private int servings;
    private String image;

    public Recipe(int id, String name, Ingredients[] ingredients, Steps[] steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    private Recipe (Parcel parcel){
        id = parcel.readInt();
        name = parcel.readString();
        ingredients = parcel.createTypedArray(Ingredients.CREATOR);
        steps = parcel.createTypedArray(Steps.CREATOR);
        servings = parcel.readInt();
        image = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeTypedArray(ingredients, PARCELABLE_WRITE_RETURN_VALUE);
        parcel.writeTypedArray(steps, PARCELABLE_WRITE_RETURN_VALUE);
        parcel.writeInt(servings);
        parcel.writeString(image);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };


    //All getter method
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}

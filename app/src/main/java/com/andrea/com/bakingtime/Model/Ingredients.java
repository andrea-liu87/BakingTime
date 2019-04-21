package com.andrea.com.bakingtime.Model;

import android.os.Parcel;
import android.os.Parcelable;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;
@SimpleSQLTable(table = "ingredients", provider = "IngredientsProvider")
public class Ingredients implements Parcelable {

    private double quantity;
    private String measure;

    @SimpleSQLColumn("col_ingredients")
    private
    String ingredient;

    public Ingredients (){}

    public Ingredients(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    private Ingredients (Parcel parcel){
        quantity = parcel.readDouble();
        measure = parcel.readString();
        ingredient = parcel.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>(){
        @Override
        public Ingredients createFromParcel(Parcel parcel) {
            return new Ingredients(parcel);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    //All getter method
    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}

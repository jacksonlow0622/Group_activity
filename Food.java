package com.example.final_group_assign_mobile_app;

public class Food {

    String cook_time;
    String foodID;
    String foodtype;
    String image;
    String ingred;
    String name;
    String prep_time;
    String rating;
    String steps;

    public Food() {
    }

    public Food(String cook_time, String foodID, String foodtype, String image, String ingred, String name, String prep_time, String rating, String steps) {
        this.cook_time = cook_time;
        this.foodID = foodID;
        this.foodtype = foodtype;
        this.image = image;
        this.ingred = ingred;
        this.name = name;
        this.prep_time = prep_time;
        this.rating = rating;
        this.steps = steps;
    }

    public String getCook_time() {
        return cook_time;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngred() {
        return ingred;
    }

    public void setIngred(String ingred) {
        this.ingred = ingred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}

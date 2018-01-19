package models;

import java.util.ArrayList;

/**
 * Created by Guest on 1/18/18.
 */
public class Restaurant {
    private String name;
    private String type;
    private Float averageRating;
    private ArrayList<Float> ratings;
    private int numberOfRatings;
    private String review;
    private String street;
    private String city;
    private String state;
    private String zip;
    private int id;

    public Restaurant(String name, String type, String street, String city, String state, String zip) {
        this.name = name;
        this.type = type;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating() {
        Float number = 0.00f;
        for (Float rating : ratings) {
            number += rating;
        };
        this.averageRating = number / getNumberOfRatings();
    }

    public Float returnAverageRating() {
        Float number = 0.00f;
        for (Float rating : ratings) {
            number += rating;
        };
        return number / getNumberOfRatings();
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (numberOfRatings != that.numberOfRatings) return false;
        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!type.equals(that.type)) return false;
        if (averageRating != null ? !averageRating.equals(that.averageRating) : that.averageRating != null)
            return false;
        if (ratings != null ? !ratings.equals(that.ratings) : that.ratings != null) return false;
        if (review != null ? !review.equals(that.review) : that.review != null) return false;
        if (!street.equals(that.street)) return false;
        if (!city.equals(that.city)) return false;
        if (!state.equals(that.state)) return false;
        return zip.equals(that.zip);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (averageRating != null ? averageRating.hashCode() : 0);
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + numberOfRatings;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zip.hashCode();
        result = 31 * result + id;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

}

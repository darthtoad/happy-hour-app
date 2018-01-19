package dao;

import models.HappyHour;
import models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 1/18/18.
 */
public interface RestaurantDao {
    void add (Restaurant restaurant);

    List<Restaurant> getAll();

    List<HappyHour> getAllHappyHoursByRestaurant(int restaurantId);

    List<Restaurant> getAllRestaurantsByCity(String city);

    Restaurant findById(int id);

    void editRestaurant(String name, String type, String street, String city, String state, String zip, int id);

    void deleteById(int id);

    void clearAllRestaurants();
}

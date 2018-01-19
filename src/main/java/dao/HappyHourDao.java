package dao;

import models.HappyHour;

import java.util.List;

/**
 * Created by Guest on 1/18/18.
 */
public interface HappyHourDao {
    void add (HappyHour happyHour);

    List<HappyHour> getAll();

    List<HappyHour> findAllHappyHoursByRestaurantId (int restaurantId);

    HappyHour findById(int id);

    void editHappyHour(String description, float price, String days, String time, int id);

    void deleteById(int id);

    void clearAll();
}

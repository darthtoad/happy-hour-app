package dao;

import models.HappyHour;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 1/18/18.
 */
public class Sql2oRestaurantDao implements RestaurantDao {
    private final Sql2o sql2o;

    public Sql2oRestaurantDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant (name, type, averageRating, ratings, numberOfRatings, review, street, city, state, zip) VALUES (:name, :type, 5, 0, 0, 0, :street, :city, :state, :zip)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Restaurant> getAll() {
        String sql = "SELECT * FROM restaurant";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Restaurant.class);
        }
    }

    @Override
    public Restaurant findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurant WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public void editRestaurant(String name, String type, String street, String city, String state, String zip, int id) {
        String sql = "UPDATE restaurant SET name = :name, type = :type, street = :street, city = :city, state = :state, zip = :zip WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("type", type)
                    .addParameter("street", street)
                    .addParameter("city", city)
                    .addParameter("state", state)
                    .addParameter("zip", zip)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from restaurant WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllRestaurants() {
        String sql = "DELETE from restaurant";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<HappyHour> getAllHappyHoursByRestaurant(int restaurantId) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM happyhour WHERE restaurantId = :restaurantId")
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(HappyHour.class);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurantsByCity(String city) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurant WHERE city = :city")
                    .addParameter("city", city)
                    .executeAndFetch(Restaurant.class);
        }
    }
}

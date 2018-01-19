package dao;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;


/**
 * Created by Guest on 1/18/18.
 */
public class Sql2oHappyHourDao implements HappyHourDao {

    private final Sql2o sql2o;

    public Sql2oHappyHourDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(HappyHour happyHour) {
        String sql = "INSERT INTO happyhour (description, price, days, time, restaurantId) VALUES (:description, :price, :days, :time, :restaurantId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(happyHour)
                    .executeUpdate()
                    .getKey();
            happyHour.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<HappyHour> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM happyhour")
                    .executeAndFetch(HappyHour.class);
        }
    }

    @Override
    public HappyHour findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM happyhour WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(HappyHour.class);
        }
    }

    @Override
    public List<HappyHour> findAllHappyHoursByRestaurantId (int restaurantId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM happyhour WHERE restaurantId = :restaurantId")
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(HappyHour.class);
        }
    }

    @Override
    public void editHappyHour(String description, float price, String days, String time, int id) {
        String sql = "UPDATE happyhour SET description = :description, price = :price, days = :days, time = :time WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("price", price)
                    .addParameter("days", days)
                    .addParameter("time", time)
                    .addParameter("id", id)
                    .executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from happyhour WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from entry";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}


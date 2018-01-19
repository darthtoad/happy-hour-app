import org.antlr.v4.runtime.misc.ObjectEqualityComparator;
import org.sql2o.Sql2o;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import dao.*;
import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static java.lang.Float.parseFloat;
import static spark.Spark.*;
/**
 * Created by Guest on 1/18/18.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/happyhour.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oRestaurantDao restaurantDao = new Sql2oRestaurantDao(sql2o);
        Sql2oHappyHourDao happyHourDao = new Sql2oHappyHourDao(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Restaurant> restaurants = restaurantDao.getAll();
            model.put("restaurants", restaurants);
            return new ModelAndView(model, "restaurants.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "restaurant-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/restaurants/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String type = request.queryParams("type");
            String street = request.queryParams("street");
            String city = request.queryParams("city");
            String state = request.queryParams("state");
            String zip = request.queryParams("zip");
            Restaurant newRestaurant = new Restaurant(name, type, street, city, state, zip);
            restaurantDao.add(newRestaurant);
            model.put("restaurant", newRestaurant);
            return new ModelAndView(model, "happyhour-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRestaurantToFind = Integer.parseInt(request.params("id"));
            Restaurant foundRestaurant = restaurantDao.findById(idOfRestaurantToFind);
            List<HappyHour> happyHours = restaurantDao.getAllHappyHoursByRestaurant(idOfRestaurantToFind);
            model.put("restaurant", foundRestaurant);
            model.put("happyhours", happyHours);
            return new ModelAndView(model, "restaurant-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants/:city", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Restaurant> restaurants = restaurantDao.getAllRestaurantsByCity(request.params("city"));
            model.put("restaurants", restaurants);
            return new ModelAndView(model, "restaurant-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRestaurantToFind = Integer.parseInt(request.params("id"));
            Restaurant foundRestaurant = restaurantDao.findById(idOfRestaurantToFind);
            model.put("restaurant", foundRestaurant);
            return new ModelAndView(model, "delete.hbs");
        }, new HandlebarsTemplateEngine());

        post("/restaurants/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRestaurantToFind = Integer.parseInt(request.params("restaurantId"));
            restaurantDao.deleteById(idOfRestaurantToFind);
            return new ModelAndView(model, "delete-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/restaurants/update/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Restaurant restaurant = restaurantDao.findById(id);
            model.put("restaurant", restaurant);
            return new ModelAndView(model, "restaurant-update-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/restaurants/update/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String type = request.queryParams("type");
            String street = request.queryParams("street");
            String city = request.queryParams("city");
            String state = request.queryParams("state");
            String zip = request.queryParams("zip");
            int id = Integer.parseInt(request.params("id"));
            restaurantDao.editRestaurant(name, type, street, city, state, zip, id);

            return new ModelAndView(model, "happyhour-update-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/happyhours/update/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String description = request.queryParams("description");
            int price = Integer.parseInt(request.queryParams("price"));
            String days = request.queryParams("days");
            String time = request.queryParams("time");
            int idOfHappyHourToEdit = Integer.parseInt(request.params("id"));
            happyHourDao.editHappyHour(description, price, days, time, idOfHappyHourToEdit);
            return new ModelAndView(model, "update-success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/restaurants/new/:id/happyhours/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String description = request.queryParams("description");
            float price = Float.parseFloat(request.queryParams("price"));
            String days = request.queryParams("days");
            String time = request.queryParams("time");
            int restaurantId = Integer.parseInt(request.params("id"));
            HappyHour newHappyHour = new HappyHour(description, price, days, time, restaurantId);
            happyHourDao.add(newHappyHour);
            model.put("happyhour", newHappyHour);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }


}

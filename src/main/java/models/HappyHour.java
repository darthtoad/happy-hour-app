package models;

/**
 * Created by Guest on 1/18/18.
 */
public class HappyHour {
    private String description;
    private float price;
    private String days;
    private String time;
    private int restaurantId;
    private int id;

    public HappyHour(String description, float price, String days, String time, int restaurantId) {
        this.description = description;
        this.price = price;
        this.days = days;
        this.time = time;
        this.restaurantId = restaurantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HappyHour happyHour = (HappyHour) o;

        if (Float.compare(happyHour.price, price) != 0) return false;
        if (restaurantId != happyHour.restaurantId) return false;
        if (id != happyHour.id) return false;
        if (!description.equals(happyHour.description)) return false;
        if (!days.equals(happyHour.days)) return false;
        return time != null ? time.equals(happyHour.time) : happyHour.time == null;
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + days.hashCode();
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + restaurantId;
        result = 31 * result + id;
        return result;
    }
}

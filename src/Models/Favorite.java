package Models;

public class Favorite {

    private int id;
    private Car car;
    private User user;
    private String date_adding;

    public Favorite(int id, Car car,  User user, String date_adding) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.date_adding = date_adding;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public User getUser() {
        return user;
    }

    public String getDate_adding() {
        return date_adding;
    }
}
